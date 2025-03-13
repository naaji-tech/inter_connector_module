package com.fitaro.interconnectormodule.product.controller;

import com.fitaro.interconnectormodule.product.model.Product;
import com.fitaro.interconnectormodule.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("productServices/v1/")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("products")
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("products")
    public ResponseEntity<Object> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("products/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable String productId) {
        return productService.getProductDetails(productId);
    }

    @PutMapping("products")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("products/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String productId) {
        return productService.deleteProduct(productId);
    }
}
