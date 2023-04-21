package com.paulfranklin.p1.dtos.responses.requests;

import javax.servlet.http.HttpServletRequest;

public class NewUserRequest {
    private String username;
    private String email;
    private String password;
    private String givenName;
    private String surname;
    private Boolean isActive;

    public NewUserRequest(HttpServletRequest request) {
        this.username = request.getParameter("username");
        this.email = request.getParameter("email");
        this.password = request.getParameter("password");
        this.givenName = request.getParameter("givenName");
        this.surname = request.getParameter("surname");
        this.isActive = true;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getSurname() {
        return surname;
    }

    public Boolean getActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
