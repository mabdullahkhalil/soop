package com.something.mabdullahk.soop;

import java.io.Serializable;

/**
 * Created by mabdullahk on 28/01/2019.
 */

public class student implements Serializable {

    String name;
    String grade;
    String attendance;
    String quizzes;
    String notes;

    public student(String name, String grade, String attendance, String quizzes, String notes) {
        this.name = name;
        this.grade = grade;
        this.attendance = attendance;
        this.quizzes = quizzes;
        this.notes = notes;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(String quizzes) {
        this.quizzes = quizzes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
