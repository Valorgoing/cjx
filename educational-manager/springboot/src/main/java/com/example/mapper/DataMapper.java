package com.example.mapper;

import com.example.entity.Data;

import java.util.List;

/**
 * 操作Data相关数据接口
*/
public interface DataMapper {

    /**
     * 新增
     */
    int insert(Data data);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Data data);

    /**
     * 根据ID查询
     */
    Data selectById(Integer id);

    /**
     * 查询所有
     */
    List<Data> selectAll(Data data);

}