package com.fitaro.interconnectormodule.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String userId;
    private String username;
    private String password;
    private String userType;

    public User(
            @JsonProperty(value = "userId") String userId,
            @JsonProperty(value = "username") String username,
            @JsonProperty(value = "password") String password,
            @JsonProperty(value = "userType") String userType
    ) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
