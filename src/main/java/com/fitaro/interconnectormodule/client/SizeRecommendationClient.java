package com.fitaro.interconnectormodule.client;

import com.fitaro.interconnectormodule.sizerecommendadtion.model.RecommendSizeDTO;
import com.fitaro.interconnectormodule.sizerecommendadtion.model.SizeRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SizeRecommendationClient {
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    @Value("${size.recommendation.server.url}")
    private String targetServerUrl;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public RecommendSizeDTO getSizeRecommendation(SizeRecommendation srBody) {
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SizeRecommendation> request = new HttpEntity<>(srBody, headers);
        ResponseEntity<RecommendSizeDTO> response = restTemplate.postForEntity(targetServerUrl, request, RecommendSizeDTO.class);

        return response.getBody();
    }
}
