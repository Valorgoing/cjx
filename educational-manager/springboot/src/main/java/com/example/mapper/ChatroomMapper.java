package com.example.mapper;

import com.example.entity.Attendance;
import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChatroomMapper {
    List<Student> getTeamList(Integer stuId);

    Teacher getTeamTeacher(Integer stuId);
}
