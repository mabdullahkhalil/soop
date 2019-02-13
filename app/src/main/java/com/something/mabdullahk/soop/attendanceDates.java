package com.something.mabdullahk.soop;

import java.util.List;

public class attendanceDates {
    List<String> date;
    List<String> attended;

    public attendanceDates(List<String> date, List<String> attended) {
        this.date = date;
        this.attended = attended;
    }


    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<String> getAttended() {
        return attended;
    }

    public void setAttended(List<String> attended) {
        this.attended = attended;
    }
}
