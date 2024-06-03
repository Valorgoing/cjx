package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.MyCourse;
import com.example.entity.MyCourse;
import com.example.exception.CustomException;
import com.example.mapper.MyCourseMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程班级信息表业务处理
 **/
@Service
public class MyCourseService {

    @Resource
    private MyCourseMapper myCourseMapper;

    /**
     * 新增
     */
    public void add(MyCourse myCourse) {
//         判断同一个学生同一门课同一天的考勤记录只能是一条
        MyCourse dbMyCourse = myCourseMapper.selectByStudentIdAndCourseIdAndClubName(myCourse.getStudentId(), myCourse.getCourseId(), myCourse.getClubName());
        if (ObjectUtil.isNotEmpty(dbMyCourse)) {
            throw new CustomException(ResultCodeEnum.ATTENDANCE_ALREADY_ERROR);
        }
        myCourseMapper.insert(myCourse);
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
    public void updateById(MyCourse myCourse) {
        myCourseMapper.updateById(myCourse);
    }

    /**
     * 根据ID查询
     */
    public MyCourse selectById(Integer id) {
        return myCourseMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<MyCourse> selectAll(MyCourse myCourse) {
        return myCourseMapper.selectAll(myCourse);
    }

    /**
     * 分页查询
     */
    public PageInfo<MyCourse> selectPage(MyCourse myCourse, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.TEACHER.name().equals(currentUser.getRole())) {
            myCourse.setTeacherId(currentUser.getId());
        }
        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
            myCourse.setStudentId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MyCourse> list = myCourseMapper.selectAll(myCourse);
        return PageInfo.of(list);
    }

}