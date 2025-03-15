package com.fitaro.interconnectormodule.usermeasurement.controller;

import com.fitaro.interconnectormodule.usermeasurement.service.UMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/userMeasurementServices/v1")
public class UMController {
    UMService umServices;

    @Autowired
    public UMController(UMService umServices) {
        this.umServices = umServices;
    }

    @GetMapping("/measurements/{username}")
    public ResponseEntity<Object> getUserMeasurements(@PathVariable String username) {
        return umServices.getUserMeasurements(username);
    }

    @PostMapping("/measurements/{username}")
    public ResponseEntity<Object> addNewUserMeasurements(
            @PathVariable String username,
            @RequestParam MultipartFile usrImage,
            @RequestParam float usrHeight
    ) {
        return umServices.addNewUserMeasurements(username, usrImage, usrHeight);
    }
}
