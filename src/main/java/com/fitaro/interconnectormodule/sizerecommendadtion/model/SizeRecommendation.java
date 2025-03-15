package com.fitaro.interconnectormodule.sizerecommendadtion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitaro.interconnectormodule.usermeasurement.model.UserMeasurement;

public class SizeRecommendation {
    private UserMeasurement userMeasurement;
    private ProductSize productMeasurement;
    private MeasurementsWeight measurementsWeight;

    public SizeRecommendation() {
        super();
    }

    public SizeRecommendation(
            @JsonProperty(value = "userMeasurements") UserMeasurement userMeasurement,
            @JsonProperty(value = "productMeasurements") ProductSize productMeasurement,
            @JsonProperty(value = "measurementsWeight") MeasurementsWeight measurementsWeight
    ) {
        this.userMeasurement = userMeasurement;
        this.productMeasurement = productMeasurement;
        this.measurementsWeight = measurementsWeight;
    }

    public UserMeasurement getUserMeasurement() {
        return userMeasurement;
    }

    public void setUserMeasurement(UserMeasurement userMeasurement) {
        this.userMeasurement = userMeasurement;
    }

    public ProductSize getProductMeasurement() {
        return productMeasurement;
    }

    public void setProductMeasurement(ProductSize productMeasurement) {
        this.productMeasurement = productMeasurement;
    }

    public MeasurementsWeight getMeasurementsWeight() {
        return measurementsWeight;
    }

    public void setMeasurementsWeight(MeasurementsWeight measurementsWeight) {
        this.measurementsWeight = measurementsWeight;
    }

    @Override
    public String toString() {
        return "SizeRecommendation{" +
                "userMeasurement=" + userMeasurement +
                ", productMeasurement=" + productMeasurement +
                ", measurementsWeight=" + measurementsWeight +
                '}';
    }
}
