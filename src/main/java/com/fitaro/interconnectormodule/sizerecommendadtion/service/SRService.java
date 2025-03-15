package com.fitaro.interconnectormodule.sizerecommendadtion.service;

import org.springframework.http.ResponseEntity;

public interface SRService {
    ResponseEntity<Object> getSizeRecommendationByMeasureMe(String username);

    ResponseEntity<Object> getSizeRecommendationByOldMeasurements(String username);
}
