package com.fitaro.interconnectormodule.client;

import com.fitaro.interconnectormodule.usermeasurement.model.UserMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UserMeasurementClient {
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private MultiValueMap<String, Object> body;

    @Value("${user.body.measure.server.url}")
    private String targetServerUrl;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    @Autowired
    public void setBody(MultiValueMap<String, Object> body) {
        this.body = body;
    }

    public UserMeasurement getUserMeasurement(MultipartFile image, float height) throws Exception{
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        ByteArrayResource imageResource = new ByteArrayResource(image.getBytes()) {
            @Override
            public String getFilename() {
                return image.getOriginalFilename();
            }
        };

        body.add("image", imageResource);
        body.add("height", height);
        body.add("weight", 58);
        body.add("age", 25);
        body.add("gender", "MALE");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<UserMeasurement> response = restTemplate.postForEntity(targetServerUrl, request, UserMeasurement.class);

        return response.getBody();
    }
}
