package com.something.mabdullahk.soop.quizzesList;

public class quizzesListClass {

    String studentId;
    String id;
    String name;
    String subjects;
    String total_questions;
    String passing_marks;
    String mark_per_question;
    String difficulty_level;
    String round;

    public quizzesListClass(String studentId, String id, String name, String subjects, String total_questions, String passing_marks, String mark_per_question, String difficulty_level, String round) {
        this.studentId = studentId;
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.total_questions = total_questions;
        this.passing_marks = passing_marks;
        this.mark_per_question = mark_per_question;
        this.difficulty_level = difficulty_level;
        this.round = round;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getTotal_questions() {
        return total_questions;
    }

    public void setTotal_questions(String total_questions) {
        this.total_questions = total_questions;
    }

    public String getPassing_marks() {
        return passing_marks;
    }

    public void setPassing_marks(String passing_marks) {
        this.passing_marks = passing_marks;
    }

    public String getMark_per_question() {
        return mark_per_question;
    }

    public void setMark_per_question(String mark_per_question) {
        this.mark_per_question = mark_per_question;
    }

    public String getDifficulty_level() {
        return difficulty_level;
    }

    public void setDifficulty_level(String difficulty_level) {
        this.difficulty_level = difficulty_level;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }
}
