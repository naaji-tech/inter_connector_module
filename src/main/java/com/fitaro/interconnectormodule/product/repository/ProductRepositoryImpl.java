package com.fitaro.interconnectormodule.product.repository;

import com.fitaro.interconnectormodule.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "productPostgresql")
public class ProductRepositoryImpl implements ProductRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addProduct(Product product) {
        String sql = "INSERT INTO products (product_id,product_name,product_description,product_category,product_price,product_image_url) VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, product.getProductId(), product.getProductName(), product.getProductDescription(), product.getProductCategory(), product.getProductPrice(), product.getProductImageUrl());
    }

    @Override
    public List<Product> getProducts() {
        String sql = """
                SELECT product_id,
                       product_name,
                       product_description,
                       product_category,
                       product_price,
                       product_image_url
                FROM products""";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
    }

    @Override
    public Product getProductDetails(String productId) {
        String sql = """
                SELECT product_id,
                       product_name,
                       product_description,
                       product_category,
                       product_price,
                       product_image_url
                FROM products WHERE product_id = ?""";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Product(
                rs.getString("product_id"),
                rs.getString("product_name"),
                rs.getString("product_description"),
                rs.getString("product_category"),
                rs.getFloat("product_price"),
                rs.getString("product_image_url")
        ), productId);
    }

    @Override
    public int updateProduct(Product product) {
        String sql = """
                UPDATE products
                SET product_name = ?,
                    product_description = ?,
                    product_price = ?,
                    last_updated_date = now()
                WHERE product_id = ?""";
        return jdbcTemplate.update(sql, product.getProductName(), product.getProductDescription(), product.getProductPrice(), product.getProductId());
    }

    @Override
    public int deleteProduct(String productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        return jdbcTemplate.update(sql, productId);
    }
}
