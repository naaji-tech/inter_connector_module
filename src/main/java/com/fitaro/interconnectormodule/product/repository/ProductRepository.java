package com.fitaro.interconnectormodule.product.repository;

import com.fitaro.interconnectormodule.product.model.Product;

import java.util.List;

public interface ProductRepository {
    int addProduct(Product product) throws Exception;

    List<Product> getProducts() throws Exception;

    Product getProductDetails(String productId) throws Exception;

    int updateProduct(Product product) throws Exception;

    int deleteProduct(String productId) throws Exception;
}
