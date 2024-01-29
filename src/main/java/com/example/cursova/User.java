package com.example.cursova;

public class User {
    private String UserName;
    private String FirstName;
    private String Surname;
    private String Password;

    public User(String userName, String firstName, String surname, String password) {
        UserName = userName;
        FirstName = firstName;
        Surname = surname;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
