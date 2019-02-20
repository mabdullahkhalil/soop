package com.something.mabdullahk.soop.quizzes;

/**
 * Created by mabdullahk on 31/01/2019.
 */

public class quizClass {
    String Id;
    String date;
    String title;
    String subject;
    String average;
    String total_marks;
    String maximum_marks;
    String marks_obtained;


    public quizClass(String id, String date, String title, String subject, String average, String total_marks, String maximum_marks, String marks_obtained) {
        Id = id;
        this.date = date;
        this.title = title;
        this.subject = subject;
        this.average = average;
        this.total_marks = total_marks;
        this.maximum_marks = maximum_marks;
        this.marks_obtained = marks_obtained;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(String total_marks) {
        this.total_marks = total_marks;
    }

    public String getMaximum_marks() {
        return maximum_marks;
    }

    public void setMaximum_marks(String maximum_marks) {
        this.maximum_marks = maximum_marks;
    }

    public String getMarks_obtained() {
        return marks_obtained;
    }

    public void setMarks_obtained(String marks_obtained) {
        this.marks_obtained = marks_obtained;
    }
}
