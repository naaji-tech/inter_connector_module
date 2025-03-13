package com.fitaro.interconnectormodule.usermeasurement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMeasurement {
    private float headCircumference;
    private float neckCircumference;
    private float shoulderToCrotchHeight;
    private float chestCircumference;
    private float waistCircumference;
    private float hipCircumference;
    private float wristCircumference;
    private float bicepCircumference;
    private float forearmCircumference;
    private float armLength;
    private float insideLegHeight;
    private float thighCircumference;
    private float calfCircumference;
    private float ankleCircumference;
    private float shoulderBreadth;
    private float height;

    public UserMeasurement(
            @JsonProperty(value = "headCircumference") float headCircumference,
            @JsonProperty(value = "neckCircumference") float neckCircumference,
            @JsonProperty(value = "shoulderToCrotchHeight") float shoulderToCrotchHeight,
            @JsonProperty(value = "chestCircumference") float chestCircumference,
            @JsonProperty(value = "waistCircumference") float waistCircumference,
            @JsonProperty(value = "hipCircumference") float hipCircumference,
            @JsonProperty(value = "wristCircumference") float wristCircumference,
            @JsonProperty(value = "bicepCircumference") float bicepCircumference,
            @JsonProperty(value = "forearmCircumference") float forearmCircumference,
            @JsonProperty(value = "armLength") float armLength,
            @JsonProperty(value = "insideLegHeight") float insideLegHeight,
            @JsonProperty(value = "thighCircumference") float thighCircumference,
            @JsonProperty(value = "calfCircumference") float calfCircumference,
            @JsonProperty(value = "ankleCircumference") float ankleCircumference,
            @JsonProperty(value = "shoulderBreadth") float shoulderBreadth,
            @JsonProperty(value = "height") float height
    ) {
        this.headCircumference = headCircumference;
        this.neckCircumference = neckCircumference;
        this.shoulderToCrotchHeight = shoulderToCrotchHeight;
        this.chestCircumference = chestCircumference;
        this.waistCircumference = waistCircumference;
        this.hipCircumference = hipCircumference;
        this.wristCircumference = wristCircumference;
        this.bicepCircumference = bicepCircumference;
        this.forearmCircumference = forearmCircumference;
        this.armLength = armLength;
        this.insideLegHeight = insideLegHeight;
        this.thighCircumference = thighCircumference;
        this.calfCircumference = calfCircumference;
        this.ankleCircumference = ankleCircumference;
        this.shoulderBreadth = shoulderBreadth;
        this.height = height;
    }

    public float getHeadCircumference() {
        return headCircumference;
    }

    public void setHeadCircumference(float headCircumference) {
        this.headCircumference = headCircumference;
    }

    public float getNeckCircumference() {
        return neckCircumference;
    }

    public void setNeckCircumference(float neckCircumference) {
        this.neckCircumference = neckCircumference;
    }

    public float getShoulderToCrotchHeight() {
        return shoulderToCrotchHeight;
    }

    public void setShoulderToCrotchHeight(float shoulderToCrotchHeight) {
        this.shoulderToCrotchHeight = shoulderToCrotchHeight;
    }

    public float getChestCircumference() {
        return chestCircumference;
    }

    public void setChestCircumference(float chestCircumference) {
        this.chestCircumference = chestCircumference;
    }

    public float getWaistCircumference() {
        return waistCircumference;
    }

    public void setWaistCircumference(float waistCircumference) {
        this.waistCircumference = waistCircumference;
    }

    public float getHipCircumference() {
        return hipCircumference;
    }

    public void setHipCircumference(float hipCircumference) {
        this.hipCircumference = hipCircumference;
    }

    public float getWristCircumference() {
        return wristCircumference;
    }

    public void setWristCircumference(float wristCircumference) {
        this.wristCircumference = wristCircumference;
    }

    public float getBicepCircumference() {
        return bicepCircumference;
    }

    public void setBicepCircumference(float bicepCircumference) {
        this.bicepCircumference = bicepCircumference;
    }

    public float getForearmCircumference() {
        return forearmCircumference;
    }

    public void setForearmCircumference(float forearmCircumference) {
        this.forearmCircumference = forearmCircumference;
    }

    public float getArmLength() {
        return armLength;
    }

    public void setArmLength(float armLength) {
        this.armLength = armLength;
    }

    public float getInsideLegHeight() {
        return insideLegHeight;
    }

    public void setInsideLegHeight(float insideLegHeight) {
        this.insideLegHeight = insideLegHeight;
    }

    public float getThighCircumference() {
        return thighCircumference;
    }

    public void setThighCircumference(float thighCircumference) {
        this.thighCircumference = thighCircumference;
    }

    public float getCalfCircumference() {
        return calfCircumference;
    }

    public void setCalfCircumference(float calfCircumference) {
        this.calfCircumference = calfCircumference;
    }

    public float getAnkleCircumference() {
        return ankleCircumference;
    }

    public void setAnkleCircumference(float ankleCircumference) {
        this.ankleCircumference = ankleCircumference;
    }

    public float getShoulderBreadth() {
        return shoulderBreadth;
    }

    public void setShoulderBreadth(float shoulderBreadth) {
        this.shoulderBreadth = shoulderBreadth;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "UserMeasurement{" +
                "headCircumference=" + headCircumference +
                ", neckCircumference=" + neckCircumference +
                ", shoulderToCrotchHeight=" + shoulderToCrotchHeight +
                ", chestCircumference=" + chestCircumference +
                ", waistCircumference=" + waistCircumference +
                ", hipCircumference=" + hipCircumference +
                ", wristCircumference=" + wristCircumference +
                ", bicepCircumference=" + bicepCircumference +
                ", forearmCircumference=" + forearmCircumference +
                ", armLength=" + armLength +
                ", insideLegHeight=" + insideLegHeight +
                ", thighCircumference=" + thighCircumference +
                ", calfCircumference=" + calfCircumference +
                ", ankleCircumference=" + ankleCircumference +
                ", shoulderBreadth=" + shoulderBreadth +
                ", height=" + height +
                '}';
    }
}
