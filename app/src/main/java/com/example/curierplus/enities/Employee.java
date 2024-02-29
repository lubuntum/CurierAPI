package com.example.curierplus.enities;

public class Employee {
    public int id;
    public String name;
    public String surname;
    public String patronymic;
    public String sessionKey;

    public Employee(int id, String name, String surname, String patronymic, String sessionKey) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.sessionKey = sessionKey;
    }
    public String getFullName(){
        return String.format("%s %s %s", surname, name, patronymic);
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
