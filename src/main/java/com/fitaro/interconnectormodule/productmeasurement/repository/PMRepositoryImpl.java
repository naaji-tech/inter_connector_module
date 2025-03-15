package com.fitaro.interconnectormodule.productmeasurement.repository;

import com.fitaro.interconnectormodule.productmeasurement.model.ProductMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(value = "pmPostgresql")
public class PMRepositoryImpl implements PMRepository{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public PMRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int[] addProductMeasurementManual(List<ProductMeasurement> productMeasurement, String productId) {
        String sql = "INSERT INTO product_measurements (product_id, product_size, sleeve_length, shoulder_width, chest, waist, bottom_circumference, front_length, sleeve) VALUES (?,?,?,?,?,?,?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (ProductMeasurement pm : productMeasurement) {
            batchArgs.add(new Object[]{
                    productId,
                    pm.getProductSize(),
                    pm.getSleeveLength(),
                    pm.getShoulderWidth(),
                    pm.getChest(),
                    pm.getWaist(),
                    pm.getBottomCircumference(),
                    pm.getFrontLength(),
                    pm.getSleeve()
            });
        }

        return jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    @Override
    public List<ProductMeasurement> getProductMeasurements(String productId) {
        String sql = "SELECT product_size, sleeve_length, shoulder_width, chest, waist, bottom_circumference, front_length, sleeve FROM product_measurements WHERE product_id = ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ProductMeasurement.class), productId);
    }

    @Override
    public int updateProductMeasurementManual(ProductMeasurement pm, String productId) {
        String sql = """
                UPDATE product_measurements
                SET sleeve_length = ?,
                    shoulder_width = ?,
                    chest = ?,
                    waist = ?,
                    bottom_circumference = ?,
                    front_length = ?,
                    sleeve = ?,
                    last_updated_date = now()
                WHERE product_size = ?
                AND product_id = ?""";
        return jdbcTemplate.update(sql, pm.getSleeveLength(), pm.getShoulderWidth(), pm.getChest(), pm.getWaist(), pm.getBottomCircumference(), pm.getFrontLength(), pm.getSleeve(), pm.getProductSize(), productId);
    }

    @Override
    public int deleteProductMeasurements(String productId) {
        String sql = "DELETE FROM product_measurements WHERE product_id = ?";
        return jdbcTemplate.update(sql, productId);
    }
}
