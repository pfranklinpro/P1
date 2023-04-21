package com.paulfranklin.p1.dtos.responses.requests;

import javax.servlet.http.HttpServletRequest;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(HttpServletRequest request) {
        this.username = request.getParameter("username");
        this.password = request.getParameter("password");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
