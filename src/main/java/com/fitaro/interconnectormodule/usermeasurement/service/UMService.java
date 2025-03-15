package com.fitaro.interconnectormodule.usermeasurement.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UMService {
    ResponseEntity<Object> getUserMeasurements(String username);

    ResponseEntity<Object> addNewUserMeasurements(String username, MultipartFile usrImage, float usrHeight);
}
