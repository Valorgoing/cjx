package com.example.common.enums;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/5/14 08:28
 */
public enum LevelEnum {
    // 组长
    HEADER("组长"),
//    STUDENT("学生"),
    A("开发经理"),
    B("产品经理"),
    C("计划经理"),
    D("测试经理"),
    E("质量经理"),
    ;
    public String level;

    LevelEnum(String level) {
        this.level = level;
    }
}
