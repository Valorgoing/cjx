package com.example.common.enums;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/5/6 09:10
 */
public enum SegmentEnum {
    FIRST("第一大节（08:00 ~ 09:35）"),
    SECOND("第二大节（09:55 ~ 11:30）"),
    THIRD("第三大节（13:15 ~ 14:50）"),
    FORTH("第四大节（15:05 ~ 16:40）"),
    FIFTH("第五大节（18:00 ~ 20:20）"),
    ;
    public String segment;

    SegmentEnum(String segment) {
        this.segment = segment;
    }
}
