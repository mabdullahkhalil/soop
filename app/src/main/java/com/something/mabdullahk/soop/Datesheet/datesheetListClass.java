package com.something.mabdullahk.soop.Datesheet;

public class datesheetListClass {

    String Sid;
    String subject;
    String date;
    String time;

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDays_to_go() {
        return days_to_go;
    }

    public void setDays_to_go(String days_to_go) {
        this.days_to_go = days_to_go;
    }

    String teacher;
    String days_to_go;


    public datesheetListClass(String sid, String subject, String date, String time, String teacher, String days_to_go) {
        Sid = sid;
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.teacher = teacher;
        this.days_to_go = days_to_go;
    }
}
