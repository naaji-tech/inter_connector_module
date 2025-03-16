package com.fitaro.interconnectormodule.sizerecommendadtion.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SRService {
    ResponseEntity<Object> getSizeRecommendationByMeasureMe(String username, String productId, MultipartFile usrImage, float usrHeight);

    ResponseEntity<Object> getSizeRecommendationByOldMeasurements(String username, String productId);
}
