package com.example.demo.domain;

import java.util.List;

public class Attendees {

    private int attendeeCount;
    private List<String> attendees;
    private String name;
    private String startDate;

    public Attendees (){

    }

    public int getAttendeeCount() {
        return attendeeCount;
    }

    public void setAttendeeCount(int attendeeCount) {
        this.attendeeCount = attendeeCount;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Attendees(int attendeeCount, List<String> attendees, String name, String startDate) {
        this.attendeeCount = attendeeCount;
        this.attendees = attendees;
        this.name = name;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "OutputResponse{" +
                "attendeeCount=" + attendeeCount +
                ", attendees=" + attendees +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
