package com.paulfranklin.p1.dtos.responses;

import com.paulfranklin.p1.models.UserRole;

public class Principal {
    private String userId;
    private String username;
    private String email;
    private String givenName;
    private String surname;
    private String role;

    public Principal(String userId, String username, String email, String givenName, String surname, UserRole userRole) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.givenName = givenName;
        this.surname = surname;
        this.role = userRole.getRole();
    }
    public Principal(String userId, String username, String email, String givenName, String surname, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.givenName = givenName;
        this.surname = surname;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
