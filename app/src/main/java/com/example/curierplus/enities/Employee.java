package com.example.curierplus.enities;

public class Employee {
    public int id;
    public String name;
    public String secondName;
    public String patronymic;
    public String sessionKey;
    public String lastAuth;

    public Employee(int id, String name, String secondName, String patronymic, String sessionKey) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.sessionKey = sessionKey;
    }

    public String getFullName(){
        return String.format("%s %s %s", secondName, name, patronymic);
    }

    public String getLastAuth() {
        return lastAuth;
    }

    public void setLastAuth(String lastAuth) {
        this.lastAuth = lastAuth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
