package com.fitaro.interconnectormodule.sizerecommendadtion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MeasurementsWeight {
    private float chest;
    private float waist;
    private float shoulderWidth;
    private float sleeveLength;
    private float frontLength;
    private float bottomCircumference;
    private float sleeve;

    public MeasurementsWeight() {
        super();
    }

    public MeasurementsWeight(
            @JsonProperty(value = "chest") float chest,
            @JsonProperty(value = "waist") float waist,
            @JsonProperty(value = "shoulderWidth") float shoulderWidth,
            @JsonProperty(value = "sleeveLength") float sleeveLength,
            @JsonProperty(value = "frontLength") float frontLength,
            @JsonProperty(value = "bottomCircumference") float bottomCircumference,
            @JsonProperty(value = "sleeve") float sleeve
    ) {
        this.chest = chest;
        this.waist = waist;
        this.shoulderWidth = shoulderWidth;
        this.sleeveLength = sleeveLength;
        this.frontLength = frontLength;
        this.bottomCircumference = bottomCircumference;
        this.sleeve = sleeve;
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

    public float getShoulderWidth() {
        return shoulderWidth;
    }

    public void setShoulderWidth(float shoulderWidth) {
        this.shoulderWidth = shoulderWidth;
    }

    public float getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(float sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public float getFrontLength() {
        return frontLength;
    }

    public void setFrontLength(float frontLength) {
        this.frontLength = frontLength;
    }

    public float getBottomCircumference() {
        return bottomCircumference;
    }

    public void setBottomCircumference(float bottomCircumference) {
        this.bottomCircumference = bottomCircumference;
    }

    public float getSleeve() {
        return sleeve;
    }

    public void setSleeve(float sleeve) {
        this.sleeve = sleeve;
    }

    @Override
    public String toString() {
        return "MeasurementsWeight{" +
                "chest=" + chest +
                ", waist=" + waist +
                ", shoulderWidth=" + shoulderWidth +
                ", sleeveLength=" + sleeveLength +
                ", frontLength=" + frontLength +
                ", bottomCircumference=" + bottomCircumference +
                ", sleeve=" + sleeve +
                '}';
    }
}
