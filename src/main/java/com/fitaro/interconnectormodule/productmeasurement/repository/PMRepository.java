package com.fitaro.interconnectormodule.productmeasurement.repository;

import com.fitaro.interconnectormodule.productmeasurement.model.ProductMeasurement;

import java.util.List;
import java.util.Map;

public interface PMRepository {
    int[] addProductMeasurementManual(List<ProductMeasurement> productMeasurements, String productId) throws Exception;

    List<ProductMeasurement> getProductMeasurements(String productId);

    int updateProductMeasurementManual(ProductMeasurement productMeasurements, String productId);

    int deleteProductMeasurements(String productId);
}
