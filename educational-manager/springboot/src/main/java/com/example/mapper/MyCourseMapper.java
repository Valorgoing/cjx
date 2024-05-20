package com.example.mapper;

import com.example.entity.Team;
import com.example.entity.Team;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作Attendance相关数据接口
*/
public interface MyCourseMapper {

    /**
     * 新增
     */
    int insert(Team team);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Team team);

    /**
     * 根据ID查询
     */
    Team selectById(Integer id);

    /**
     * 查询所有
     */
    List<Team> selectAll(Team team);

//    @Select("select * from team where student_id = #{studentId} and course_id = #{courseId} and time = #{time}")
//    Team selectByStudentIdAndCourseIdAndTime(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId, @Param("time") String time);
}