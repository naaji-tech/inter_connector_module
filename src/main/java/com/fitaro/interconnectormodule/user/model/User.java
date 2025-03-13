package com.fitaro.interconnectormodule.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String userId;
    private String userName;
    private String password;
    private String userType;

    public User(
            @JsonProperty(value = "userId") String userId,
            @JsonProperty(value = "userName") String userName,
            @JsonProperty(value = "password") String password,
            @JsonProperty(value = "userType") String userType
    ) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
