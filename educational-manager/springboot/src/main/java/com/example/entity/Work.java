package com.example.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务信息表
 */
public class Work implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    private Integer assignerStudentId; // 任务分配者学生ID
    private String content;
    private Integer courseId;
    private String file;
    private Date startDate;
    private Date endDate;
    private String assignerName; // 任务分配者的名字
    private String courseName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }


    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getAssignerStudentId() {
        return assignerStudentId;
    }

    public void setAssignerStudentId(Integer assignerStudentId) {
        this.assignerStudentId = assignerStudentId;
    }

    public String getAssignerName() {
        return assignerName;
    }

    public void setAssignerName(String assignerName) {
        this.assignerName = assignerName;
    }
}
