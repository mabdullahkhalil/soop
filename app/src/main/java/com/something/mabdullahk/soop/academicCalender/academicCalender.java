package com.something.mabdullahk.soop.academicCalender;

import java.util.List;

public class academicCalender {
    List<String> start_date;
    List<String> end_date;
    List<String> type;
    List<String> title;
    List<String> date;
    List<String> days_to_go;
    List<String> description;


    public List<String> getStart_date() {
        return start_date;
    }

    public void setStart_date(List<String> start_date) {
        this.start_date = start_date;
    }

    public List<String> getEnd_date() {
        return end_date;
    }

    public void setEnd_date(List<String> end_date) {
        this.end_date = end_date;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<String> getDays_to_go() {
        return days_to_go;
    }

    public void setDays_to_go(List<String> days_to_go) {
        this.days_to_go = days_to_go;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public academicCalender(List<String> start_date,
                            List<String> end_date,
                            List<String> type,
                            List<String> title,
                            List<String> date,
                            List<String> days_to_go,
                            List<String> description) {
        this.date = date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.type = type;
        this.title = title;
        this.days_to_go = days_to_go;
        this.description = description;
    }
}
