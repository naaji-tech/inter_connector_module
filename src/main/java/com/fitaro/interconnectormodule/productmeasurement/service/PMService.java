package com.fitaro.interconnectormodule.productmeasurement.service;

import com.fitaro.interconnectormodule.productmeasurement.model.ProductMeasurement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PMService {
    ResponseEntity<Object> addProductMeasurementByManual(List<ProductMeasurement> productMeasurements, String productId);

    ResponseEntity<Object> addProductMeasurementByScan(String productId, MultipartFile imageXS, MultipartFile imageS, MultipartFile imageM, MultipartFile imageL, MultipartFile imageXL);

    ResponseEntity<Object> getProductMeasurements(String productId);

    ResponseEntity<Object> updateProductMeasurementManual(ProductMeasurement productMeasurements, String productId);

    ResponseEntity<Object> deleteProductMeasurements(String productId);
}
