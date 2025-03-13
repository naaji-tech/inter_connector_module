package com.fitaro.interconnectormodule.productsize.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductSize {
    private String productSize;
    private ProductMeasurement productMeasurement;

    public ProductSize(
            @JsonProperty(value = "productSize") String productSize,
            @JsonProperty(value = "productMeasurement") ProductMeasurement productMeasurement) {
        this.productSize = productSize;
        this.productMeasurement = productMeasurement;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public ProductMeasurement getProductMeasurement() {
        return productMeasurement;
    }

    public void setProductMeasurement(ProductMeasurement productMeasurement) {
        this.productMeasurement = productMeasurement;
    }

    @Override
    public String toString() {
        return "ProductSize{" +
                "productSize='" + productSize + '\'' +
                ", productMeasurement=" + productMeasurement +
                '}';
    }
}
