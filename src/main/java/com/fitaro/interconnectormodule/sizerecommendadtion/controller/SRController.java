package com.fitaro.interconnectormodule.sizerecommendadtion.controller;

import com.fitaro.interconnectormodule.sizerecommendadtion.service.SRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sizeRecommendationServices/v1")
public class SRController {
    private SRService srService;

    @Autowired
    public SRController(SRService srService) {
        this.srService = srService;
    }

    @GetMapping("/scanMeasurements/{username}")
    public ResponseEntity<Object> getSizeRecommendationByMeasureMe(@PathVariable String username) {
        return srService.getSizeRecommendationByMeasureMe(username);
    }

    @GetMapping("/oldMeasurements/{username}")
    public ResponseEntity<Object> getSizeRecommendationByOldMeasurements(@PathVariable String username) {
        return srService.getSizeRecommendationByOldMeasurements(username);
    }
}
