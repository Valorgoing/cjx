package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChatroomMapper {
    List<Student> getTeamMemberList(String teamName);

    Teacher getTeamTeacher(String teamName);

    String getTeamName(Integer stuId);

    List<String> getTeamList();

    @MapKey("sender")
    List<Map<String, String>> getChatHistoryForTeam(String teamName);

    void truncateHistory();
    void saveChatHistoryForTeam(String teamName, @Param("historyList") List<Map<String, String>> historyList);
}
