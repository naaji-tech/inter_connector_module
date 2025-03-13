package com.fitaro.interconnectormodule.util;

public enum Error {
    // common database errors
    DATABASE_EXCEPTION(-501, "Internal server error"),
    DUPLICATE_KEY_EXCEPTION(-510, "Username already exist"),

    // user module related errors
    USER_NOT_FOUND(-404, "User details not found"),
    USERNAME_NOT_FOUND(-410, "User not found"),
    WRONG_PASSWORD(-411, "Wrong password"),
    USERNAME_UPDATE_FAILED(-412, "Update username failed"),
    PASSWORD_UPDATE_FAILED(-413, "Update password failed"),

    // user measurement related errors
    USER_MEASUREMENT_NOT_FOUND(-414, "User measurement not found"),
    USER_IMAGE_MISSING(-415, "User image is empty"),
    UM_CLIENT_CONNECTION_FAILED(-416, "User measurements client connection failed"),
    ADD_NEW_USER_MEASUREMENT_FAILED(-417, "User measurements adding failed");

    private final int value;
    private final String reasonPhrase;

    Error(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
