package com.fitaro.interconnectormodule.sizerecommendadtion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitaro.interconnectormodule.productmeasurement.model.ProductMeasurement;

public class ProductSize {
    private ProductMeasurement xs;
    private ProductMeasurement s;
    private ProductMeasurement m;
    private ProductMeasurement l;
    private ProductMeasurement xl;

    public ProductSize() {
        super();
    }

    public ProductSize(
            @JsonProperty(value = "XS") ProductMeasurement xs,
            @JsonProperty(value = "S") ProductMeasurement s,
            @JsonProperty(value = "M") ProductMeasurement m,
            @JsonProperty(value = "L") ProductMeasurement l,
            @JsonProperty(value = "XL") ProductMeasurement xl
    ) {
        this.xs = xs;
        this.s = s;
        this.m = m;
        this.l = l;
        this.xl = xl;
    }

    public ProductMeasurement getXs() {
        return xs;
    }

    public void setXs(ProductMeasurement xs) {
        this.xs = xs;
    }

    public ProductMeasurement getS() {
        return s;
    }

    public void setS(ProductMeasurement s) {
        this.s = s;
    }

    public ProductMeasurement getM() {
        return m;
    }

    public void setM(ProductMeasurement m) {
        this.m = m;
    }

    public ProductMeasurement getL() {
        return l;
    }

    public void setL(ProductMeasurement l) {
        this.l = l;
    }

    public ProductMeasurement getXl() {
        return xl;
    }

    public void setXl(ProductMeasurement xl) {
        this.xl = xl;
    }

    @Override
    public String toString() {
        return "ProductSize{" +
                "xs=" + xs +
                ", s=" + s +
                ", m=" + m +
                ", l=" + l +
                ", xl=" + xl +
                '}';
    }
}
