package com.example.mapper;

import com.example.entity.Work;

import java.util.List;

/**
 * 操作Data相关数据接口
 */
public interface WorkMapper {

    /**
     * 新增
     */
    int insert(Work work);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Work work);

    /**
     * 根据ID查询
     */
    Work selectById(Integer id);

    /**
     * 查询所有
     */
    List<Work> selectAll(Work work);

}