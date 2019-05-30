package com.example.checkit.Data;

public class LeaderModel {
    String id;
    String firstName;
    String lastName;
    String workerId;
    String email;

    public LeaderModel(String id, String firstName, String lastName, String workerId, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workerId = workerId;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getWorkerId() {
        return workerId;
    }

    public String getEmail() {
        return email;
    }
}
