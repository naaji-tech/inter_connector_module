package com.fitaro.interconnectormodule;

import com.fitaro.interconnectormodule.client.ProductMeasurementClient;
import com.fitaro.interconnectormodule.client.UserMeasurementClient;
import com.fitaro.interconnectormodule.productmeasurement.model.ProductMeasurement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public UserMeasurementClient userMeasurementClient() {
        return new UserMeasurementClient();
    }

    @Bean
    public ProductMeasurementClient productMeasurementClient() {
        return new ProductMeasurementClient();
    }
}
