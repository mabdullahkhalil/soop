package com.something.mabdullahk.soop.announcements;

/**
 * Created by mabdullahk on 06/02/2019.
 */

public class announcement {
    String teacher;
    String title;
    String time;
    String description;


    public announcement(String teacher, String title, String time, String description) {
        this.teacher = teacher;
        this.title = title;
        this.time = time;
        this.description = description;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
