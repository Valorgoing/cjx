package com.example.component;

import com.example.config.ApplicationContextHolder;
import com.example.entity.*;
import com.example.mapper.ChatroomMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chatroom/{usrInfo}")
@Component
public class ChatSocketServer {
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<Map<String, String>> chatHistory = new ArrayList<>();
    ChatroomMapper chatroomMapper;

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "usrInfo") String usrInfo) throws IOException {
        chatroomMapper = ApplicationContextHolder.getContext().getBean(ChatroomMapper.class);

        sessionMap.put(usrInfo, session);
        System.out.println(usrInfo + "in");
        System.out.println(sessionMap.size());

        updateAllSessionUserList();
        updateAllSessionChatHistory();
    }

    @OnClose
    public void onClose(@PathParam(value = "usrInfo") String usrInfo) {
        sessionMap.remove(usrInfo);
        System.out.println(usrInfo + " leave");
        System.out.println("remain" + sessionMap.size());

        updateAllSessionUserList();
    }

    @OnMessage
    public void onMessage(String msg, Session session, @PathParam(value = "usrInfo") String usrInfo) throws JsonProcessingException {
        Map<String, String> message = objectMapper.readValue(msg, Map.class);
        chatHistory.add(message);
        updateAllSessionChatHistory();
    }

    public void sendUserList(String usrInfo) {
        String[] l = usrInfo.split("-");
        String role = l[0];
        String id = l[1];

        List<Student> students = chatroomMapper.getTeamList(Integer.parseInt(id));
        Teacher teacher = chatroomMapper.getTeamTeacher(Integer.parseInt(id));

        List<Map<String, Object>> response = new ArrayList<>();
        Map<String, Object> t_pairs = new HashMap<>();
        t_pairs.put("name", teacher.getName());
        t_pairs.put("online", false);
        response.add(t_pairs);

        for (final Student stu : students) {
            if (id.equals(stu.getId().toString()) && "STUDENT".equals(role)) continue;

            Map<String, Object> pairs = new HashMap<>();
            pairs.put("name", stu.getName());
            if (sessionMap.containsKey("STUDENT-" + stu.getId().toString()))
                pairs.put("online", true);
            else
                pairs.put("online", false);
            response.add(pairs);
        }


        try {
            Session session = sessionMap.get(usrInfo);
            String json = objectMapper.writeValueAsString(response);
            session.getBasicRemote().sendText(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAllSessionUserList() {
        for (final String usrInfo : sessionMap.keySet()) {
            sendUserList(usrInfo);
        }
    }

    public void sendChatHistory(String usrInfo) {
        String[] l = usrInfo.split("-");
        String id = l[1];

        List<Student> students = chatroomMapper.getTeamList(Integer.parseInt(id));
        Teacher teacher = chatroomMapper.getTeamTeacher(Integer.parseInt(id));

        List<Map<String, String>> response = new ArrayList<>();
        for (final Map<String, String> aMsg : chatHistory) {
            if (students.stream().anyMatch(stu -> stu.getName().equals(aMsg.get("sender"))) ||
                teacher.getName().equals(aMsg.get("sender"))) {
                response.add(aMsg);
            }
        }

        try {
            Session session = sessionMap.get(usrInfo);
            String json = objectMapper.writeValueAsString(response);
            session.getBasicRemote().sendText(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAllSessionChatHistory() {
        for (final String usrInfo : sessionMap.keySet()) {
            sendChatHistory(usrInfo);
        }
    }

}
