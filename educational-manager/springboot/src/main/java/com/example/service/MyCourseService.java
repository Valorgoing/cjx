package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Team;
import com.example.exception.CustomException;
import com.example.mapper.MyCourseMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考勤信息表业务处理
 **/
@Service
public class MyCourseService {

    @Resource
    private MyCourseMapper myCourseMapper;

    /**
     * 新增
     */
    public void add(Team team) {
        // 判断同一个学生同一门课同一天的考勤记录只能是一条
//        Team dbAttendance = myCourseMapper.selectByStudentIdAndCourseIdAndTime(team.getStudentId(), team.getCourseId(), team.getTime());
//        if (ObjectUtil.isNotEmpty(dbAttendance)) {
//            throw new CustomException(ResultCodeEnum.ATTENDANCE_ALREADY_ERROR);
//        }
        myCourseMapper.insert(team);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        myCourseMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            myCourseMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Team team) {
        myCourseMapper.updateById(team);
    }

    /**
     * 根据ID查询
     */
    public Team selectById(Integer id) {
        return myCourseMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Team> selectAll(Team team) {
        return myCourseMapper.selectAll(team);
    }

    /**
     * 分页查询
     */
    public PageInfo<Team> selectPage(Team team, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.TEACHER.name().equals(currentUser.getRole())) {
            team.setTeacherId(currentUser.getId());
        }
        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
            team.setStudentId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Team> list = myCourseMapper.selectAll(team);
        return PageInfo.of(list);
    }

}