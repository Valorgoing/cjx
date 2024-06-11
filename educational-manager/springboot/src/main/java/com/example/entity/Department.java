package com.example.entity;

import java.io.Serializable;

/**
 * 考勤信息表
*/
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private String img;
    private String name;
    private String description;
    private Integer studentId;
    private String studentName;  // 新添加的属性

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}