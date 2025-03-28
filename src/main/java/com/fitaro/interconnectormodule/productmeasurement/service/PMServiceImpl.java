package com.fitaro.interconnectormodule.productmeasurement.service;

import com.fitaro.interconnectormodule.client.ProductMeasurementClient;
import com.fitaro.interconnectormodule.productmeasurement.model.ProductMeasurement;
import com.fitaro.interconnectormodule.productmeasurement.repository.PMRepository;
import com.fitaro.interconnectormodule.util.Error;
import com.fitaro.interconnectormodule.util.ResHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PMServiceImpl implements PMService {
    PMRepository pmRepository;
    ProductMeasurementClient pmClient;

    @Autowired
    public PMServiceImpl(@Qualifier("pmPostgresql") PMRepository pmRepository, ProductMeasurementClient pmClient) {
        this.pmRepository = pmRepository;
        this.pmClient = pmClient;
    }

    @Override
    public ResponseEntity<Object> addProductMeasurementByManual(List<ProductMeasurement> productMeasurements, String productId) {
        try {
            int[] res = pmRepository.addProductMeasurementManual(productMeasurements, productId);

            if (res.length < 1)
                return ResHandler.error(Error.ADD_PRODUCT_MEASUREMENT_MANUAL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

            return ResHandler.success("Add product measurement by manual success", HttpStatus.CREATED);

        } catch (DuplicateKeyException e) {
            System.out.println("duplicate key exception : " + e.getMessage());
            return ResHandler.error(Error.DUPLICATE_KEY_EXCEPTION, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> addProductMeasurementByScan(String productId, MultipartFile imageXS, MultipartFile imageS, MultipartFile imageM, MultipartFile imageL, MultipartFile imageXL) {
        try {
            if (imageXS.isEmpty() && imageS.isEmpty() && imageM.isEmpty() && imageL.isEmpty() && imageXL.isEmpty())
                return ResHandler.error(Error.PRODUCT_IMAGE_IS_EMPTY, HttpStatus.BAD_REQUEST);

            List<ProductMeasurement> pm = pmRepository.getProductMeasurements(productId);
            if (!pm.isEmpty()) {
                pm.clear();
                return ResHandler.error(Error.PRODUCT_MEASUREMENTS_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
            }

            List<MultipartFile> images = Arrays.asList(imageXS, imageS, imageM, imageL, imageXL);
            List<ProductMeasurement> productMeasurements = pmClient.getProductMeasurements(images);

            System.out.println("product measurements size: " + productMeasurements.toString());

            int[] res = pmRepository.addProductMeasurementManual(productMeasurements, productId);
            productMeasurements.clear();
            if (res.length < 1)
                return ResHandler.error(Error.ADD_PRODUCT_MEASUREMENT_MANUAL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

            return ResHandler.success("Add product measurement by scan success", HttpStatus.CREATED);

        } catch (DuplicateKeyException e) {
            System.out.println("duplicate key exception : " + e.getMessage());
            return ResHandler.error(Error.DUPLICATE_KEY_EXCEPTION, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getProductMeasurements(String productId) {
        try {
            List<ProductMeasurement> pm = pmRepository.getProductMeasurements(productId);

            if (pm.isEmpty())
                return ResHandler.error(Error.PRODUCT_MEASUREMENTS_NOT_FOUND, HttpStatus.NOT_FOUND);

            return ResHandler.success("Get product measurement success", pm, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> updateProductMeasurementManual(ProductMeasurement productMeasurements, String productId) {
        try {
            int res = pmRepository.updateProductMeasurementManual(productMeasurements, productId);

            if (res < 1)
                return ResHandler.error(Error.PRODUCT_MEASUREMENTS_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

            return ResHandler.success("Update product manual measurement success", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> deleteProductMeasurements(String productId) {
        try {
            int res = pmRepository.deleteProductMeasurements(productId);

            if (res < 1)
                return ResHandler.error(Error.PRODUCT_MEASUREMENTS_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

            return ResHandler.success("Delete product measurement success", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
