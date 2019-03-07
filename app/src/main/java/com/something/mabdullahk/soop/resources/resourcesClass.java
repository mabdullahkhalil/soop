package com.something.mabdullahk.soop.resources;

public class resourcesClass {
    String teacherName;
    String title;
    String subject;
    String url;
    String uploadTime;

    public resourcesClass(String teacherName, String title, String subject, String url, String uploadTime) {
        this.teacherName = teacherName;
        this.title = title;
        this.subject = subject;
        this.url = url;
        this.uploadTime = uploadTime;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }
}
