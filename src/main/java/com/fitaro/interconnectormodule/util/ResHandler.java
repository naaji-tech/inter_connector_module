package com.fitaro.interconnectormodule.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResHandler {
    public static ResponseEntity<Object> success(String message, Object data, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("statusCode", httpStatus.value());
        response.put("message", message);
        response.put("data", data);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> success(String message, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("statusCode", httpStatus.value());
        response.put("message", message);
        response.put("data", null);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> error(Error error, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("statusCode", error.getValue());
        response.put("message", error.getReasonPhrase());
        response.put("data", null);
        return new ResponseEntity<>(response, httpStatus);
    }
}
