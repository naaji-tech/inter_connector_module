package com.fitaro.interconnectormodule.productmeasurement.controller;

import com.fitaro.interconnectormodule.productmeasurement.model.ProductMeasurement;
import com.fitaro.interconnectormodule.productmeasurement.service.PMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/productMeasurementServices/v1")
public class PMController {
    PMService pmService;

    @Autowired
    public PMController(PMService pmService) {
        this.pmService = pmService;
    }

    @PostMapping("/manualMeasurements/{productId}")
    public ResponseEntity<Object> addProductMeasurementByManual(@RequestBody List<ProductMeasurement> productMeasurements, @PathVariable String productId) {
        return pmService.addProductMeasurementByManual(productMeasurements, productId);
    }

    @PostMapping("/scanMeasurements/{productId}")
    public ResponseEntity<Object> addProductMeasurementByScan(
        @PathVariable String productId,
        @RequestParam MultipartFile imageXS,
        @RequestParam MultipartFile imageS,
        @RequestParam MultipartFile imageM,
        @RequestParam MultipartFile imageL,
        @RequestParam MultipartFile imageXL
    ) {
        return pmService.addProductMeasurementByScan(productId, imageXS, imageS, imageM, imageL, imageXL);
    }

    @GetMapping("/measurements/{productId}")
    public ResponseEntity<Object> getProductMeasurements(@PathVariable String productId) {
        return pmService.getProductMeasurements(productId);
    }

    @PutMapping("/measurements/{productId}")
    public ResponseEntity<Object> updateProductMeasurementManual(@RequestBody ProductMeasurement productMeasurements, @PathVariable String productId) {
        return pmService.updateProductMeasurementManual(productMeasurements, productId);
    }

    @DeleteMapping("/measurements/{productId}")
    public ResponseEntity<Object> deleteProductMeasurement(@PathVariable String productId) {
        return pmService.deleteProductMeasurements(productId);
    }
}
