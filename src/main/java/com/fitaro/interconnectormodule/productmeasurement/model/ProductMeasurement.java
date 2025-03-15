package com.fitaro.interconnectormodule.productmeasurement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductMeasurement {
    private String productSize;
    private float sleeveLength;
    private float shoulderWidth;
    private float chest;
    private float waist;
    private float bottomCircumference;
    private float frontLength;
    private float sleeve;

    public ProductMeasurement() {
        super();
    }

    public ProductMeasurement(
            @JsonProperty(value = "productSize") String productSize,
            @JsonProperty(value = "sleeveLength") float sleeveLength,
            @JsonProperty(value = "shoulderWidth") float shoulderWidth,
            @JsonProperty(value = "chest") float chest,
            @JsonProperty(value = "waist") float waist,
            @JsonProperty(value = "bottomCircumference") float bottomCircumference,
            @JsonProperty(value = "frontLength") float frontLength,
            @JsonProperty(value = "sleeve") float sleeve
    ) {
        this.productSize = productSize;
        this.sleeveLength = sleeveLength;
        this.shoulderWidth = shoulderWidth;
        this.chest = chest;
        this.waist = waist;
        this.bottomCircumference = bottomCircumference;
        this.frontLength = frontLength;
        this.sleeve = sleeve;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public float getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(float sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public float getShoulderWidth() {
        return shoulderWidth;
    }

    public void setShoulderWidth(float shoulderWidth) {
        this.shoulderWidth = shoulderWidth;
    }

    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getBottomCircumference() {
        return bottomCircumference;
    }

    public void setBottomCircumference(float bottomCircumference) {
        this.bottomCircumference = bottomCircumference;
    }

    public float getFrontLength() {
        return frontLength;
    }

    public void setFrontLength(float frontLength) {
        this.frontLength = frontLength;
    }

    public float getSleeve() {
        return sleeve;
    }

    public void setSleeve(float sleeve) {
        this.sleeve = sleeve;
    }

    @Override
    public String toString() {
        return "ProductMeasurement{" +
                "productSize='" + productSize + '\'' +
                ", sleeveLength=" + sleeveLength +
                ", shoulderWidth=" + shoulderWidth +
                ", chest=" + chest +
                ", waist=" + waist +
                ", bottomCircumference=" + bottomCircumference +
                ", frontLength=" + frontLength +
                ", sleeve=" + sleeve +
                '}';
    }
}
