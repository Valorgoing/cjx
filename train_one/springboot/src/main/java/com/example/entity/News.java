package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/4/29 14:57
 */
@Data
public class News {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String content;
    private Integer authorid;
    private String time;

    //这个注释表示这个字段不在数据库里，是用来做业务处理用的
    @TableField(exist = false)
    private String author;
}
