package com.something.mabdullahk.soop.students;

import java.io.Serializable;

/**
 * Created by mabdullahk on 28/01/2019.
 */

public class student implements Serializable {

    String name;
    String roll_number;
    String id;
    String grade;
    String attendance;
    String quizzes;
    String notes;
    String announcements;


    public student(String name, String roll_number, String id, String grade, String attendance, String quizzes, String notes, String announcements) {
        this.name = name;
        this.roll_number = roll_number;
        this.id = id;
        this.grade = grade;
        this.attendance = attendance;
        this.quizzes = quizzes;
        this.notes = notes;
        this.announcements = announcements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(String announcements) {
        this.announcements = announcements;
    }
}
