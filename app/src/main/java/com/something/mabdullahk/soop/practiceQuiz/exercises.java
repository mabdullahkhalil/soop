package com.something.mabdullahk.soop.practiceQuiz;

public class exercises {
    String studentId;
String name;
String id;
String quiz_competition_type;
String subjects;
String rounds;
String number_of_quizzes;
String Number_of_questions;

    public exercises(String studentId, String name, String id, String quiz_competition_type, String subjects, String rounds, String number_of_quizzes, String number_of_questions) {
        this.studentId = studentId;
        this.name = name;
        this.id = id;
        this.quiz_competition_type = quiz_competition_type;
        this.subjects = subjects;
        this.rounds = rounds;
        this.number_of_quizzes = number_of_quizzes;
        Number_of_questions = number_of_questions;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuiz_competition_type() {
        return quiz_competition_type;
    }

    public void setQuiz_competition_type(String quiz_competition_type) {
        this.quiz_competition_type = quiz_competition_type;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getRounds() {
        return rounds;
    }

    public void setRounds(String rounds) {
        this.rounds = rounds;
    }

    public String getNumber_of_quizzes() {
        return number_of_quizzes;
    }

    public void setNumber_of_quizzes(String number_of_quizzes) {
        this.number_of_quizzes = number_of_quizzes;
    }

    public String getNumber_of_questions() {
        return Number_of_questions;
    }

    public void setNumber_of_questions(String number_of_questions) {
        Number_of_questions = number_of_questions;
    }
}

