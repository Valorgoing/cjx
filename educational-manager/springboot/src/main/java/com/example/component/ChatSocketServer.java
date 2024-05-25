package com.example.component;

import com.example.config.ApplicationContextHolder;
import com.example.entity.*;
import com.example.mapper.ChatroomMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chatroom/{usrInfo}")
@Component
@DependsOn("applicationContextHolder")
public class ChatSocketServer {
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    public static final Map<String, List<Session>> teamSessionMap = new ConcurrentHashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Map<String, List<Map<String, String>>> chatHistory = new HashMap<>();
    ChatroomMapper chatroomMapper;

    @PostConstruct
    public void init() {
        // 加载聊天记录
        chatroomMapper = ApplicationContextHolder.getContext().getBean(ChatroomMapper.class);
        List<String> allTeams = chatroomMapper.getTeamList();
        for (String team : allTeams) {
            List<Map<String, String>> history = chatroomMapper.getChatHistoryForTeam(team);
            chatHistory.put(team, history);
        }
    }

    @PreDestroy
    public void destroy() {
        chatroomMapper.truncateHistory();
        // 保存聊天记录
        for (Map.Entry<String, List<Map<String, String>>> entry : chatHistory.entrySet()) {
            chatroomMapper.saveChatHistoryForTeam(entry.getKey(), entry.getValue());
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "usrInfo") String usrInfo) throws IOException {
        chatroomMapper = ApplicationContextHolder.getContext().getBean(ChatroomMapper.class);
        sessionMap.put(usrInfo, session);

        String teamName = chatroomMapper.getTeamName(parseId(usrInfo));
        if (!chatHistory.containsKey(teamName)) chatHistory.put(teamName, new ArrayList<>());
        if (!teamSessionMap.containsKey(teamName)) teamSessionMap.put(teamName, new ArrayList<>());

        teamSessionMap.get(teamName).add(session);

        updateTeamUserList(teamName);
        updateTeamChatHistory(teamName);
    }

    @OnClose
    public void onClose(Session session, @PathParam(value = "usrInfo") String usrInfo) throws IOException {
        sessionMap.remove(usrInfo);
        System.out.println(usrInfo + " leave");
        System.out.println("remain" + sessionMap.size());

        String teamName = chatroomMapper.getTeamName(parseId(usrInfo));
        teamSessionMap.get(teamName).remove(session);
        updateTeamUserList(teamName);
    }

    @OnMessage
    public void onMessage(String msg, Session session, @PathParam(value = "usrInfo") String usrInfo) throws JsonProcessingException {
        String teamName = chatroomMapper.getTeamName(parseId(usrInfo));

        Map<String, String> message = objectMapper.readValue(msg, Map.class);
        chatHistory.get(teamName).add(message);
        updateTeamChatHistory(teamName);
    }

    public String getUserList(String teamName) {

        List<Student> students = chatroomMapper.getTeamMemberList(teamName);
        Teacher teacher = chatroomMapper.getTeamTeacher(teamName);

        List<Map<String, Object>> response = new ArrayList<>();
        Map<String, Object> t_pairs = new HashMap<>();
        t_pairs.put("name", teacher.getName());
        t_pairs.put("online", false);
        response.add(t_pairs);

        for (final Student stu : students) {
            Map<String, Object> pairs = new HashMap<>();
            pairs.put("name", stu.getName());
            if (sessionMap.containsKey("STUDENT-" + stu.getId().toString()))
                pairs.put("online", true);
            else
                pairs.put("online", false);
            response.add(pairs);
        }


        try {
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateTeamUserList(String teamName) throws IOException {
        for (final Session session : teamSessionMap.get(teamName)) {
            String json = getUserList(teamName);
            session.getBasicRemote().sendText(json);
        }
    }

    public void updateTeamChatHistory(String teamName) {
        for (final Session session : teamSessionMap.get(teamName)) {
            try {
                String json = objectMapper.writeValueAsString(chatHistory.get(teamName));
                session.getBasicRemote().sendText(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Integer parseId(String usrInfo) {
        String[] l = usrInfo.split("-");
        return Integer.parseInt(l[1]);
    }

}
