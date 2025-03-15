package com.fitaro.interconnectormodule.client;

import com.fitaro.interconnectormodule.productmeasurement.model.ProductMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Component
public class ProductMeasurementClient {
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private MultiValueMap<String, Object> body;
    private List<ProductMeasurement> pmResult;

    @Value("${product.measure.server.url}")
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

    @Autowired
    public void setPmResult(List<ProductMeasurement> pmResult) {
        this.pmResult = pmResult;
    }

    public List<ProductMeasurement> getProductMeasurements(List<MultipartFile> images) throws Exception {
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        for (MultipartFile image : images) {
            if (image.isEmpty())
                continue;

            ByteArrayResource imageResource = new ByteArrayResource(image.getBytes()) {
                @Override
                public String getFilename() {
                    return image.getOriginalFilename();
                }
            };

            body.add("image", imageResource);

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
            ResponseEntity<ProductMeasurement> response = restTemplate.postForEntity(targetServerUrl, request, ProductMeasurement.class);

            Objects.requireNonNull(response.getBody()).setProductSize(image.getName().split("image")[1]);
            pmResult.add(response.getBody());
            System.out.println("Image body response: " + response.getBody());
        }

        return pmResult;
    }
}
