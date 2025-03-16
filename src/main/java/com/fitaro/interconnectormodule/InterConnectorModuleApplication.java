package com.fitaro.interconnectormodule;

import com.fitaro.interconnectormodule.client.ProductMeasurementClient;
import com.fitaro.interconnectormodule.client.SizeRecommendationClient;
import com.fitaro.interconnectormodule.client.UserMeasurementClient;
import com.fitaro.interconnectormodule.productmeasurement.model.ProductMeasurement;
import com.fitaro.interconnectormodule.sizerecommendadtion.model.ProductSize;
import com.fitaro.interconnectormodule.sizerecommendadtion.model.SizeRecommendation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class InterConnectorModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterConnectorModuleApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders headers() {
        return new HttpHeaders();
    }

    @Bean
    public MultiValueMap<String, Object> body() {
        return new LinkedMultiValueMap<>();
    }

    @Bean
    public List<ProductMeasurement> pmResult() {
        return new ArrayList<>();
    }

    @Bean
    public SizeRecommendation sizeRecommendation() {
        return new SizeRecommendation();
    }

    @Bean
    public ProductSize productSize() {
        return new ProductSize();
    }

    @Bean
    public UserMeasurementClient userMeasurementClient() {
        return new UserMeasurementClient();
    }

    @Bean
    public ProductMeasurementClient productMeasurementClient() {
        return new ProductMeasurementClient();
    }

    @Bean
    public SizeRecommendationClient sizeRecommendationClient() {
        return new SizeRecommendationClient();
    }
}
