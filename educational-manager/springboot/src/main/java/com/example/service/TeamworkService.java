package com.example.service;

import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Teamwork;
import com.example.mapper.TeamworkMapper;
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
public class TeamworkService {

    @Resource
    private TeamworkMapper teamworkMapper;

    /**
     * 新增
     */
    public void add(Teamwork teamwork) {
        teamworkMapper.insert(teamwork);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        teamworkMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            teamworkMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Teamwork teamwork) {
        teamworkMapper.updateById(teamwork);
    }

    /**
     * 根据ID查询
     */
    public Teamwork selectById(Integer id) {
        return teamworkMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Teamwork> selectAll(Teamwork teamwork) {
        return teamworkMapper.selectAll(teamwork);
    }

    /**
     * 分页查询
     */
//    public PageInfo<Teamwork> selectPage(Teamwork teamwork, Integer pageNum, Integer pageSize) {
//        Account currentUser = TokenUtils.getCurrentUser();
//        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
//            teamwork.setStudentId(currentUser.getId());
//        }
//        PageHelper.startPage(pageNum, pageSize);
//        List<Teamwork> list = teamworkMapper.selectAll(teamwork);
//        return PageInfo.of(list);
//    }
    public PageInfo<Teamwork> selectPage(Teamwork teamwork, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teamwork> list = teamworkMapper.selectAll(teamwork);
        return PageInfo.of(list);
    }
    /**
     * 根据departmentId查询
     */
//    public List<Teamwork> selectByDepartmentId(Integer departmentId) {
//        Teamwork teamwork = new Teamwork();
//        teamwork.setDepartmentId(departmentId);
//        return teamworkMapper.selectAll(teamwork);
//    }
}
