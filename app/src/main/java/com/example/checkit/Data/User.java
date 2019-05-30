package com.example.checkit.Data;

public class User {
int id;
String name;
String surname;
String authority;
String mail;
String password;

    public User(String name, String surname, String authority, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.authority = authority;
        this.mail = email;
        this.password = password;
    }
    public User(String name, String surname, String authority, String email, String password,int id) {
        this.name = name;
        this.surname = surname;
        this.authority = authority;
        this.mail = email;
        this.password = password;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAuthority() {
        return authority;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }


}
