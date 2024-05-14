package com.example.common.enums;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/5/14 08:28
 */
public enum LevelEnum {
    // 社长
    HEADER("社长"),
    STUDENT("学生"),
    ;
    public String level;

    LevelEnum(String level) {
        this.level = level;
    }
}
