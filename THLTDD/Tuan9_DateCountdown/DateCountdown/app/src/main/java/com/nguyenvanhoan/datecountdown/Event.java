package com.nguyenvanhoan.datecountdown;

public class Event {
    private String title;
    private String event;
    private String day;

    public Event(String title, String event, String day) {
        this.title = title;
        this.event = event;
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
