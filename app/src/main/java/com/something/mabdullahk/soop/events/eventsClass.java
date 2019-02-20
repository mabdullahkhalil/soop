package com.something.mabdullahk.soop.events;

import com.something.mabdullahk.soop.R;

public class eventsClass {

    String title;
    String time;
    String toGo;
    String type;
    String description;


    public eventsClass(String title, String time, String toGo, String type, String description) {
        this.title = title;
        this.time = time;
        this.toGo = toGo;
        this.type = type;
        this.description = description;
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

    public String getToGo() {
        return toGo;
    }

    public void setToGo(String toGo) {
        this.toGo = toGo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public int findColor(){
        switch (this.type){
            case "holiday":
                return R.color.colorlogo1;
            case "exams":
                return R.color.colorlogo2;
            case "function":
                return R.color.colorlogo1;
            case "social_activity":
                return R.color.colorlogo3;
            case "parent_teacher_meeting":
                return R.color.colorlogo4;

        }
        return R.color.colorlogo4;
    }


    public int findImage(){
        switch (this.type){
            case "holiday":
                return R.drawable.events_holidays;
            case "exams":
                return R.drawable.events_exams;
            case "function":
                return R.drawable.events_functions;
            case "social_activity":
                return R.drawable.events_social;
            case "parent_teacher_meeting":
                return R.drawable.events_meeting;

        }
        return 0;
    }
}
