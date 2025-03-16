package com.fitaro.interconnectormodule.util;

public enum Error {
    // common database errors
    DATABASE_EXCEPTION(-501, "Internal server error"),
    DUPLICATE_KEY_EXCEPTION(-510, "Already existing"),

    // user module related errors
    USER_NOT_FOUND(-404, "User details not found"),
    USERNAME_NOT_FOUND(-410, "User not found"),
    WRONG_PASSWORD(-411, "Wrong password"),
    USERNAME_UPDATE_FAILED(-502, "Update username failed"),
    PASSWORD_UPDATE_FAILED(-503, "Update password failed"),

    // user measurement related errors
    USER_MEASUREMENT_NOT_FOUND(-414, "User measurement not found"),
    USER_IMAGE_MISSING(-415, "User image is empty"),
    UM_CLIENT_CONNECTION_FAILED(-416, "User measurements client connection failed"),
    ADD_NEW_USER_MEASUREMENT_FAILED(-417, "User measurements adding failed"),

    // product module related errors
    ADD_PRODUCT_FAILED(-504, "Product adding failed"),
    PRODUCT_UPDATE_FAILED(-505, "Product update failed"),
    PRODUCT_DELETE_FAILED(-506, "Product delete failed"),
    PRODUCT_NOT_FOUND(-418, "Product not found" ),

    // product measurement module related errors
    ADD_PRODUCT_MEASUREMENT_MANUAL_FAILED(-507, "Add product measurement manually failed"),
    PRODUCT_MEASUREMENTS_NOT_FOUND(-419, "Product measurements not found"),
    PRODUCT_MEASUREMENTS_UPDATE_FAILED(-508, "Product measurement update failed" ),
    PRODUCT_MEASUREMENTS_DELETE_FAILED(-509, "Product measurements delete failed" ),
    PRODUCT_IMAGE_IS_EMPTY(-420, "at least one image needed"),

    // size recommendation module related errors
    MEASUREMENT_WEIGHT_NOT_FOUND(-421, "Measurement weight not found");

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
