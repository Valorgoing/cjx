package com.example.common.enums;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/5/6 09:10
 */
public enum WeekEnum {
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期天"),
    ;
    public String week;

    WeekEnum(String week) {
        this.week = week;
    }
}
