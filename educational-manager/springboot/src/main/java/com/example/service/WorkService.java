package com.example.service;

import com.example.entity.Course;
import com.example.entity.Work;
import com.example.mapper.CourseMapper;
import com.example.mapper.WorkMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程资料表业务处理
 **/
@Service
public class WorkService {

    @Resource
    private WorkMapper workMapper;
    @Resource
    private CourseMapper courseMapper;

    /**
     * 新增
     */
    public void add(Work work) {
        // Assume the studentId has been set correctly by the client
        // We can add additional logic here if needed.
        workMapper.insert(work);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        workMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        ids.forEach(workMapper::deleteById);
    }

    /**
     * 修改
     */
    public void updateById(Work work) {
        workMapper.updateById(work);
    }

    /**
     * 根据ID查询
     */
    public Work selectById(Integer id) {
        return workMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Work> selectAll(Work work) {
        return workMapper.selectAll(work);
    }

    /**
     * 分页查询
     */
    public PageInfo<Work> selectPage(Work work, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Work> list = workMapper.selectAll(work);
        return PageInfo.of(list);
    }
}
