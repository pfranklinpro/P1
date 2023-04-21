package com.paulfranklin.p1.models;

public class User {
//    CREATE TABLE ERS_USERS (
//            USER_ID VARCHAR(255) PRIMARY KEY,
//    USERNAME VARCHAR(255) UNIQUE NOT NULL,
//    EMAIL VARCHAR(255) UNIQUE NOT NULL,
//    PASSWORD VARCHAR(255) NOT NULL,
//    GIVEN_NAME VARCHAR(255) NOT NULL,
//    SURNAME VARCHAR(255) NOT NULL,
//    IS_ACTIVE BOOLEAN,
//    ROLE_ID VARCHAR(255) REFERENCES ERS_USER_ROLES(ROLE_ID)
//            );
    private String userId;
    private String username;
    private String email;
    private String password;
    private String givenName;
    private String surname;
    private Boolean isActive;
    private UserRole role;

    public User(String userId, String username, String email, String password, String givenName, String surname, Boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
        this.role = new UserRole("0","EMPLOYEE");
    }
    public User(String userId, String username, String email, String password, String givenName, String surname, Boolean isActive, UserRole userRole) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
        this.role = userRole;
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

    public UserRole getRole() {
        return role;
    }
}
