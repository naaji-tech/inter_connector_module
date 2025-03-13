package com.fitaro.interconnectormodule.product.service;

import com.fitaro.interconnectormodule.product.model.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<Object> addProduct(Product product);

    ResponseEntity<Object> getProducts();

    ResponseEntity<Object> getProductDetails(String productId);

    ResponseEntity<Object> updateProduct(Product product);

    ResponseEntity<Object> deleteProduct(String productId);
}
