package com.example.service;

import com.example.common.enums.RoleEnum;
import com.example.entity.*;
import com.example.mapper.CourseMapper;
import com.example.mapper.TestMapper;
import com.example.mapper.TeacherMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作业信息表业务处理
 **/
@Service
public class TestService {

    @Resource
    private TestMapper testMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 新增
     */
    public void add(Test test) {
        Course course = courseMapper.selectById(test.getCourseId());
        Teacher teacher = teacherMapper.selectById(course.getTeacherId());
        test.setTeacherId(teacher.getId());
        testMapper.insert(test);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        testMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            testMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Test test) {
        testMapper.updateById(test);
    }

    /**
     * 根据ID查询
     */
    public Test selectById(Integer id) {
        return testMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Test> selectAll(Test test) {
        return testMapper.selectAll(test);
    }

    /**
     * 分页查询
     */
    public PageInfo<Test> selectPage(Test test, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
            test.setStudentId(currentUser.getId());
        }
        if (RoleEnum.TEACHER.name().equals(currentUser.getRole())) {
            test.setTeacherId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Test> list = testMapper.selectAll(test);
        return PageInfo.of(list);
    }

}