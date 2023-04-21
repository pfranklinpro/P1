package com.paulfranklin.p1.models;

public class UserRole {
//    CREATE TABLE ERS_USER_ROLES (
//            ROLE_ID VARCHAR(255) PRIMARY KEY,
//    ROLE VARCHAR(255) UNIQUE
//);
    private String roleId;
    private String role;

    public UserRole(String roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRole() {
        return role;
    }
}
