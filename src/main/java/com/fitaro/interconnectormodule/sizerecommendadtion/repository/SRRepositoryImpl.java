package com.fitaro.interconnectormodule.sizerecommendadtion.repository;

import com.fitaro.interconnectormodule.sizerecommendadtion.model.MeasurementsWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "srPostgresql")
public class SRRepositoryImpl implements SRRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SRRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MeasurementsWeight getMeasurementWeights(String productId) {
        String sql = """
                SELECT sleeve_length, shoulder_width, chest, waist, bottom_circumference, front_length, sleeve
                FROM product_weights
                WHERE product_type = (SELECT DISTINCT product_type
                                      from products
                                      WHERE product_id = ?)""";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            float sleeveLength = rs.getFloat("sleeve_length");
            float shoulderWidth = rs.getFloat("shoulder_width");
            float chest = rs.getFloat("chest");
            float waist = rs.getFloat("waist");
            float bottomCircumference = rs.getFloat("bottom_circumference");
            float frontLength = rs.getFloat("front_length");
            float sleeve = rs.getFloat("sleeve");
            return new MeasurementsWeight(chest, waist, shoulderWidth, sleeveLength, frontLength, bottomCircumference, sleeve);
        }, productId);
    }
}
