package com.example.checkit.Data;

public class EventModel {
String id ;
String name;
LeaderModel leaderModel;

    public EventModel(String id, String name, LeaderModel leaderModel) {
        this.id = id;
        this.name = name;
        this.leaderModel = leaderModel;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LeaderModel getLeaderModel() {
        return leaderModel;
    }
}
