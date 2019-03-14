package com.something.mabdullahk.soop.questions;


import java.util.List;

public class questionsClass {

    String id;
    String statement;
    List<answersClass> options;

    public questionsClass(String id, String statement, List<answersClass> options) {
        this.id = id;
        this.statement = statement;
        this.options = options;
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

    public List<answersClass> getOptions() {
        return options;
    }

    public void setOptions(List<answersClass> options) {
        this.options = options;
    }
}
