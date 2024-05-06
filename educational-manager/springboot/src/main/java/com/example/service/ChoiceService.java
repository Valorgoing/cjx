package com.example.service;

import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Choice;
import com.example.entity.Classes;
import com.example.entity.Course;
import com.example.exception.CustomException;
import com.example.mapper.ChoiceMapper;
import com.example.mapper.ClassesMapper;
import com.example.mapper.CourseMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 选课信息表业务处理
 **/
@Service
public class ChoiceService {

    @Resource
    private ChoiceMapper choiceMapper;
    @Resource
    private CourseMapper courseMapper;

    /**
     * 新增
     */
    public void add(Choice choice) {
        // 当前选的课
        Course course = courseMapper.selectById(choice.getCourseId());
        // 1. 先判断一下该门课选满了没有
        // 1) 我们可以在选课信息表里面查询该门课被选的次数（用这种方式）
        // 2) 这种方式大家自己去尝试：在课程信息表里面加个字段（已选人数），判断当前这门课的这个已选人数字段是否已经等于上课人数字段的值
        List<Choice> list = choiceMapper.selectByCourseId(choice.getCourseId());
        if (course.getNum().equals(list.size())) {
            throw new CustomException(ResultCodeEnum.COURSE_NUM_ERROR);
        }

        // 2. 判断该学生选的这么课的开课时间有没有和他之前选的课有冲突
        List<Choice> sList = choiceMapper.selectByStudentId(choice.getStudentId());
        for (Choice dbChoice : sList) {
            Course tmpCourse = courseMapper.selectById(dbChoice.getCourseId());
            if (course.getWeek().equals(tmpCourse.getWeek()) && course.getSegment().equals(tmpCourse.getSegment())) {
                throw new CustomException("-1", "您之前已经选过" + tmpCourse.getName() + ", 与该门课的上课时间冲突，请重新选择");
            }
        }

        choiceMapper.insert(choice);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        choiceMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            choiceMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Choice choice) {
        choiceMapper.updateById(choice);
    }

    /**
     * 根据ID查询
     */
    public Choice selectById(Integer id) {
        return choiceMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Choice> selectAll(Choice choice) {
        return choiceMapper.selectAll(choice);
    }

    /**
     * 分页查询
     */
    public PageInfo<Choice> selectPage(Choice choice, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.TEACHER.name().equals(currentUser.getRole())) {
            choice.setTeacherId(currentUser.getId());
        }
        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
            choice.setStudentId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Choice> list = choiceMapper.selectAll(choice);
        return PageInfo.of(list);
    }

}