package com.example.mapper;

import com.example.entity.Test;

import java.util.List;

/**
 * 操作Homework相关数据接口
 */
public interface TestMapper {

    /**
     * 新增
     */
    int insert(Test test);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Test test);

    /**
     * 根据ID查询
     */
    Test selectById(Integer id);

    /**
     * 查询所有
     */
    List<Test> selectAll(Test test);

}