package com.something.mabdullahk.soop.questions;


import java.util.List;

public class questionsClass {

    String id;
    String statement;
    String subject;
    List<answersClass> options;
    String nextQuiz;

    public questionsClass(String id, String statement, String subject, List<answersClass> options, String nextQuiz) {
        this.id = id;
        this.statement = statement;
        this.subject = subject;
        this.options = options;
        this.nextQuiz = nextQuiz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<answersClass> getOptions() {
        return options;
    }

    public void setOptions(List<answersClass> options) {
        this.options = options;
    }

    public String getNextQuiz() {
        return nextQuiz;
    }

    public void setNextQuiz(String nextQuiz) {
        this.nextQuiz = nextQuiz;
    }
}
