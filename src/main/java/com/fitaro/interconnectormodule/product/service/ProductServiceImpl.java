package com.fitaro.interconnectormodule.product.service;

import com.fitaro.interconnectormodule.product.model.Product;
import com.fitaro.interconnectormodule.product.repository.ProductRepository;
import com.fitaro.interconnectormodule.util.Error;
import com.fitaro.interconnectormodule.util.ResHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(@Qualifier("productPostgresql") ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Object> addProduct(Product product) {
        try {
            int res = productRepository.addProduct(product);

            if (res < 1)
                return ResHandler.error(Error.ADD_PRODUCT_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

            return ResHandler.success("Product added success", HttpStatus.CREATED);

        } catch (DuplicateKeyException e) {
            System.out.println("duplicate key exception : " + e.getMessage());
            return ResHandler.error(Error.DUPLICATE_KEY_EXCEPTION, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getProducts() {
        try {
            List<Product> products = productRepository.getProducts();
            return ResHandler.success("Get products success", products, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getProductDetails(String productId) {
        try {
            Product product = productRepository.getProductDetails(productId);

            return ResHandler.success("Get product details success",product , HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
            return ResHandler.error(Error.PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> updateProduct(Product product) {
        try {
            int res = productRepository.updateProduct(product);

            if (res < 1)
                return ResHandler.error(Error.PRODUCT_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

            return ResHandler.success("Update product success", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> deleteProduct(String productId) {
        try {
            int res = productRepository.deleteProduct(productId);

            if (res < 1)
                return ResHandler.error(Error.PRODUCT_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

            return ResHandler.success("Delete product success", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
