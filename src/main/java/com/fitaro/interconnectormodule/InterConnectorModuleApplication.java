package com.fitaro.interconnectormodule;

import com.fitaro.interconnectormodule.client.UserMeasurementClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
    public UserMeasurementClient userMeasurementClient() {
        return new UserMeasurementClient();
    }
}
