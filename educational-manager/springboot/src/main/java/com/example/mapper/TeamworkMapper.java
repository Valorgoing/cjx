package com.example.mapper;

import com.example.entity.Teamwork;

import java.util.List;

/**
 * 操作Teamwork相关数据接口
 */
public interface TeamworkMapper {

    /**
     * 新增
     */
    int insert(Teamwork teamwork);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Teamwork teamwork);

    /**
     * 根据ID查询
     */
    Teamwork selectById(Integer id);

    /**
     * 查询所有
     */
    List<Teamwork> selectAll(Teamwork teamwork);

    /**
     * 根据groupId查询
     */
    List<Teamwork> selectByGroupId(Integer groupId);  // 新增根据groupId查询的方法
}
