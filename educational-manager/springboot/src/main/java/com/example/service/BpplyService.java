package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.LevelEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Bpply;
import com.example.entity.Department;
import com.example.entity.Student;
import com.example.exception.CustomException;
import com.example.mapper.BpplyMapper;
import com.example.mapper.DepartmentMapper;
import com.example.mapper.StudentMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 请假信息表业务处理
 **/
@Service
public class BpplyService {

    @Resource
    private BpplyMapper bpplyMapper;
    @Resource
    private StudentMapper userMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 新增
     */
    public void add(Bpply bpply) {
        // 先去查询一下该学生有没有申请过该社团
        List<Bpply> list = bpplyMapper.selectByStatus(bpply.getUserId(), bpply.getDepartmentId());
        if (CollectionUtil.isNotEmpty(list)) {
            throw new CustomException(ResultCodeEnum.APPLY_ALREADY_ERROR);
        }
//        bpply.setProcess(bpplyEnum.PROCESS_HEADER_APPLYING.status);
//        bpply.setStatus(bpplyEnum.STATUS_APPLYING.status);
        bpply.setProcess("processing");
        bpply.setStatus("applying");
        bpplyMapper.insert(bpply);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        bpplyMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            bpplyMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Bpply bpply) {
        bpplyMapper.updateById(bpply);
    }

    /**
     * 根据ID查询
     */
    public Bpply selectById(Integer id) {
        return bpplyMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Bpply> selectAll(Bpply bpply) {
        return bpplyMapper.selectAll(bpply);
    }

    /**
     * 分页查询
     */
    public PageInfo<Bpply> selectPage(Bpply bpply, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
            Student user = userMapper.selectById(currentUser.getId());
            if (LevelEnum.HEADER.level.equals(user.getLevel())) {
                Department department = departmentMapper.selectByUserId(user.getId());
                if (ObjectUtil.isNotEmpty(department)) {
                    bpply.setDepartmentId(department.getId());
                }
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Bpply> list = bpplyMapper.selectAll(bpply);
        return PageInfo.of(list);
    }

    public PageInfo<Bpply> selectPage2(Bpply bpply, Integer pageNum, Integer pageSize) {
        extracted(bpply);
        bpply.setStatus("ok");
        PageHelper.startPage(pageNum, pageSize);
        List<Bpply> list = bpplyMapper.selectAll(bpply);
        return PageInfo.of(list);
    }

    private void extracted(Bpply bpply) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
            Student student = userMapper.selectById(currentUser.getId());
            if (LevelEnum.HEADER.level.equals(student.getLevel())) {
                Department department = departmentMapper.selectByUserId(student.getId());
                if (ObjectUtil.isNotEmpty(department)) {
                    bpply.setDepartmentId(department.getId());
                }
            }
        }
    }
}