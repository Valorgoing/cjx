package com.example.service;

import com.example.entity.*;
import com.example.mapper.CourseMapper;
import com.example.mapper.DataMapper;
import com.example.mapper.TeacherMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程资料表业务处理
 **/
@Service
public class DataService {

    @Resource
    private DataMapper dataMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 新增
     */
    public void add(Data data) {
        Course course = courseMapper.selectById(data.getCourseId());
        Teacher teacher = teacherMapper.selectById(course.getTeacherId());
        data.setTeacherId(teacher.getId());
        dataMapper.insert(data);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        dataMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            dataMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Data data) {
        dataMapper.updateById(data);
    }

    /**
     * 根据ID查询
     */
    public Data selectById(Integer id) {
        return dataMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Data> selectAll(Data data) {
        return dataMapper.selectAll(data);
    }

    /**
     * 分页查询
     */
//    public PageInfo<Data> selectPage(Data data, Integer pageNum, Integer pageSize) {
//        Account currentUser = TokenUtils.getCurrentUser();
//        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
//            data.setStudentId(currentUser.getId());
//        }
//        if (RoleEnum.TEACHER.name().equals(currentUser.getRole())) {
//            data.setTeacherId(currentUser.getId());
//        }
//        PageHelper.startPage(pageNum, pageSize);
//        List<Data> list = dataMapper.selectAll(data);
//        return PageInfo.of(list);
//    }
    public PageInfo<Data> selectPage(Data data, Integer pageNum, Integer pageSize) {
        // 移除对学生ID和教师ID的设置，改为直接使用课程ID进行查询
        PageHelper.startPage(pageNum, pageSize);
        List<Data> list = dataMapper.selectAll(data);
        return PageInfo.of(list);
    }

}