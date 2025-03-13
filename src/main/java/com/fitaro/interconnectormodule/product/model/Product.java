package com.fitaro.interconnectormodule.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private String productId;
    private String productName;
    private String productDescription;
    private String productCategory;
    private float productPrice;
    private String productImageUrl;

    public Product() {
        super();
    }

    public Product(
            @JsonProperty(value = "productId") String productId,
            @JsonProperty(value = "productName") String productName,
            @JsonProperty(value = "productDescription") String productDescription,
            @JsonProperty(value = "productCategory") String productCategory,
            @JsonProperty(value = "productPrice") float productPrice,
            @JsonProperty(value = "productImageUrl") String productImageUrl
    ) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productPrice=" + productPrice +
                ", productImageUrl='" + productImageUrl + '\'' +
                '}';
    }
}
