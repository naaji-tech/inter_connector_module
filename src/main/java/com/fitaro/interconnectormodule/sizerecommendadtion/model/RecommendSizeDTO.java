package com.fitaro.interconnectormodule.sizerecommendadtion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecommendSizeDTO {
    private String recommendSize;

    public RecommendSizeDTO(@JsonProperty("recommendSize") String recommendSize) {
        this.recommendSize = recommendSize;
    }

    public String getRecommendSize() {
        return recommendSize;
    }

    public void setRecommendSize(String recommendSize) {
        this.recommendSize = recommendSize;
    }

    @Override
    public String toString() {
        return "RecommendSizeDTO{" +
                "recommendSize='" + recommendSize + '\'' +
                '}';
    }
}
