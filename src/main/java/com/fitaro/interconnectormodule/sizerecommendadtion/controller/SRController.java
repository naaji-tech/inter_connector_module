package com.fitaro.interconnectormodule.sizerecommendadtion.controller;

import com.fitaro.interconnectormodule.sizerecommendadtion.service.SRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/sizeRecommendationServices/v1")
public class SRController {
    private final SRService srService;

    @Autowired
    public SRController(SRService srService) {
        this.srService = srService;
    }

    @GetMapping("/scanMeasurements/{username}/{productId}")
    public ResponseEntity<Object> getSizeRecommendationByMeasureMe(@PathVariable String username, @PathVariable String productId, @RequestParam(value = "usrImage") MultipartFile usrImage, @RequestParam(value = "usrHeight") float usrHeight) {
        ResponseEntity<Object> response = srService.getSizeRecommendationByMeasureMe(username, productId, usrImage, usrHeight);
        System.out.println(response);
        return response;
    }

    @GetMapping("/oldMeasurements/{username}/{productId}")
    public ResponseEntity<Object> getSizeRecommendationByOldMeasurements(@PathVariable String username, @PathVariable String productId) {
        ResponseEntity<Object> response = srService.getSizeRecommendationByOldMeasurements(username, productId);
        System.out.println(response);
        return response;
    }
}
