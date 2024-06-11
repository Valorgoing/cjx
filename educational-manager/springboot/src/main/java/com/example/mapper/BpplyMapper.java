package com.example.mapper;

import com.example.entity.Bpply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作bpply相关数据接口
*/
public interface BpplyMapper {

    /**
     * 新增
     */
    int insert(Bpply bpply);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Bpply bpply);

    /**
     * 根据ID查询
     */
    Bpply selectById(Integer id);

    /**
     * 查询所有
     */
    List<Bpply> selectAll(Bpply bpply);

    @Select("select * from bpply where student_id = #{userId} and department_id = #{departmentId} and (status = '待审核' or status = '审核通过')")
    List<Bpply> selectByStatus(@Param("userId") Integer userId, @Param("departmentId") Integer departmentId);

}