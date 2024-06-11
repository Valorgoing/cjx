package com.example.entity;

import java.io.Serializable;

/**
 * 成绩
 */
public class Score implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Integer teacherId;
    //    private Double ordinaryScore;
    private Double ordinaryScore1;
    private Double ordinaryScore2;
    private Double ordinaryScore3;
    private Double ordinaryScore4;
    private Double ordinaryScore5;
    private Double ordinaryScore6;

    private Double examScore;
    private Double score;

    private String studentName;
    private String courseName;
    private String teacherName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

//    public Double getOrdinaryScore() {
//        return ordinaryScore;
//    }
//
//    public void setOrdinaryScore(Double ordinaryScore) {
//        this.ordinaryScore = ordinaryScore;
//    }

    public Double getOrdinaryScore1() {
        return ordinaryScore1;
    }

    public void setOrdinaryScore1(Double ordinaryScore1) {
        this.ordinaryScore1 = ordinaryScore1;
    }

    public Double getOrdinaryScore2() {
        return ordinaryScore2;
    }

    public void setOrdinaryScore2(Double ordinaryScore2) {
        this.ordinaryScore2 = ordinaryScore2;
    }

    public Double getOrdinaryScore3() {
        return ordinaryScore3;
    }

    public void setOrdinaryScore3(Double ordinaryScore3) {
        this.ordinaryScore3 = ordinaryScore3;
    }

    public Double getOrdinaryScore4() {
        return ordinaryScore4;
    }

    public void setOrdinaryScore4(Double ordinaryScore4) {
        this.ordinaryScore4 = ordinaryScore4;
    }

    public Double getOrdinaryScore5() {
        return ordinaryScore5;
    }

    public void setOrdinaryScore5(Double ordinaryScore5) {
        this.ordinaryScore5 = ordinaryScore5;
    }

    public Double getOrdinaryScore6() {
        return ordinaryScore6;
    }

    public void setOrdinaryScore6(Double ordinaryScore6) {
        this.ordinaryScore6 = ordinaryScore6;
    }

    public Double getExamScore() {
        return examScore;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }

    public Double getScore() {
        return score == null ? 0.0 : score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}