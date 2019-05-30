package com.example.checkit.Data;

public class OldEventModel {
    private String eventName;
    private String organizer;

    public OldEventModel(String eventName, String organizer){
        this.eventName=eventName;
        this.organizer = organizer;
    }

    public String getEventName() {
        return eventName;
    }
    public String getOrganizer(){
        return organizer;
    }
}
