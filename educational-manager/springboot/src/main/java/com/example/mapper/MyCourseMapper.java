package com.example.mapper;

import com.example.entity.MyCourse;
import com.example.entity.MyCourse;
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
    int insert(MyCourse myCourse);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(MyCourse myCourse);

    /**
     * 根据ID查询
     */
    MyCourse selectById(Integer id);

    /**
     * 查询所有
     */
    List<MyCourse> selectAll(MyCourse myCourse);

    @Select("select * from myCourse where student_id = #{studentId} and course_id = #{courseId} and club_name = #{clubName}")
    MyCourse selectByStudentIdAndCourseIdAndClubName(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId, @Param("clubName") String clubName);
}