package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;

import com.example.entity.Department;
import com.example.entity.Student;
import com.example.exception.CustomException;
import com.example.mapper.DepartmentMapper;
import com.example.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 小组信息表业务处理
 **/
@Service
public class DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
//    private UserMapper userMapper;
    private StudentMapper studentMapper;

    /**
     * 新增
     */
    public void add(Department department) {
        if (ObjectUtil.isNotEmpty(department.getStudentId())) {
            Department dbDepartment = departmentMapper.selectByUserId(department.getStudentId());
            if (ObjectUtil.isNotEmpty(dbDepartment)) {
                throw new CustomException(ResultCodeEnum.HEADER_ALREADY_ERROR);
            }
        }
        departmentMapper.insert(department);
        Department info = departmentMapper.selectByUserId(department.getStudentId());
        // 往学生信息表里该学生的小组信息同步一条数据
        Student student = studentMapper.selectById(department.getStudentId());
        student.setDepartmentId(info.getId());
        studentMapper.updateById(student);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        departmentMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            departmentMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Department department) {
        if (ObjectUtil.isNotEmpty(department.getStudentId())) {
            Department dbDepartment = departmentMapper.selectByUserId(department.getStudentId());
            if (ObjectUtil.isNotEmpty(dbDepartment)) {
                throw new CustomException(ResultCodeEnum.HEADER_ALREADY_ERROR);
            }
        }
        departmentMapper.updateById(department);
        // 往学生信息表里该学生的小组信息同步一条数据
        Student student = studentMapper.selectById(department.getStudentId());
        student.setDepartmentId(department.getId());
        studentMapper.updateById(student);
    }

    /**
     * 根据ID查询
     */
    public Department selectById(Integer id) {
        return departmentMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Department> selectAll(Department department) {
        return departmentMapper.selectAll(department);
    }

    /**
     * 分页查询
     */
    public PageInfo<Department> selectPage(Department department, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Department> list = departmentMapper.selectAll(department);
        return PageInfo.of(list);
    }

}