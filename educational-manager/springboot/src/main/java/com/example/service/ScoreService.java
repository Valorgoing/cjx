package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.example.mapper.CollegeMapper;
import com.example.mapper.CourseMapper;
import com.example.mapper.ScoreMapper;
import com.example.mapper.StudentMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 成绩信息表业务处理
 **/
@Service
public class ScoreService {

    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private StudentMapper studentMapper;

    /**
     * 新增
     */
    public void add(Score score) {
        // 判断下该门课该学生 有没有录入过成绩，如果有就不需要在录入了
        Score dbScore = scoreMapper.selectByCourceIdAndStudentId(score.getCourseId(), score.getStudentId());
        if (ObjectUtil.isNotEmpty(dbScore)) {
            throw new CustomException(ResultCodeEnum.SCORE_ALREADY_ERROR);
        }
        updateTotalScore(score);
        scoreMapper.insert(score);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        Score score = scoreMapper.selectById(id);
        scoreMapper.deleteById(id);
        // 扣除学生对应的学分
        Student student = studentMapper.selectById(score.getStudentId());
        Course course = courseMapper.selectById(score.getCourseId());
        student.setScore(student.getScore() - course.getScore());
        studentMapper.updateById(student);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            scoreMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Score score) {
        updateTotalScore(score);
        scoreMapper.updateById(score);
    }
    private void updateTotalScore(Score score) {
        if (areAllScoresPresent(score)) {
            double total = calculateTotalScore(score);
            score.setScore(total);
            if (total >= 60) {
                updateStudentScore(score);
            }
        } else {
            score.setScore(null); // 如果分数不完整，设置总成绩为null
        }
    }

    private boolean areAllScoresPresent(Score score) {
        return score.getOrdinaryScore1() != null && score.getOrdinaryScore2() != null &&
                score.getOrdinaryScore3() != null && score.getOrdinaryScore4() != null &&
                score.getOrdinaryScore5() != null && score.getOrdinaryScore6() != null &&
                score.getExamScore() != null;
    }

    private double calculateTotalScore(Score score) {
        return (score.getOrdinaryScore1() + score.getOrdinaryScore2() +
                score.getOrdinaryScore3() + score.getOrdinaryScore4() +
                score.getOrdinaryScore5() + score.getOrdinaryScore6()) / 6 * 0.3 +
                score.getExamScore() * 0.7;
    }

    private void updateStudentScore(Score score) {
        Course course = courseMapper.selectById(score.getCourseId());
        Student student = studentMapper.selectById(score.getStudentId());
        student.setScore(student.getScore() + course.getScore());
        studentMapper.updateById(student);
    }
    /**
     * 根据ID查询
     */
    public Score selectById(Integer id) {
        return scoreMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Score> selectAll(Score score) {
        return scoreMapper.selectAll(score);
    }

    /**
     * 分页查询
     */
    public PageInfo<Score> selectPage(Score score, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.TEACHER.name().equals(currentUser.getRole())) {
            score.setTeacherId(currentUser.getId());
        }
        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
            score.setStudentId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Score> list = scoreMapper.selectAll(score);
        return PageInfo.of(list);
    }

}