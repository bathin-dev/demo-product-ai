package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    
    private final JdbcClient jdbcClient;
    
    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setProductId(rs.getString("product_id"));
        product.setTitle(rs.getString("title"));
        product.setDescription(rs.getString("description"));
        product.setVendor(rs.getString("vendor"));
        product.setProductType(rs.getString("product_type"));
        product.setImageUrl(rs.getString("image_url"));
        product.setPrice(rs.getBigDecimal("price"));
        return product;
    };
    
    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    
    public List<Product> findAll() {
        return jdbcClient.sql("SELECT * FROM products")
                .query(productRowMapper)
                .list();
    }
    
    public Optional<Product> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM products WHERE id = :id")
                .param("id", id)
                .query(productRowMapper)
                .optional();
    }
    
    public Optional<Product> findByProductId(String productId) {
        return jdbcClient.sql("SELECT * FROM products WHERE product_id = :productId")
                .param("productId", productId)
                .query(productRowMapper)
                .optional();
    }
    
    public boolean existsByProductId(String productId) {
        Long count = jdbcClient.sql("SELECT COUNT(*) FROM products WHERE product_id = :productId")
                .param("productId", productId)
                .query(Long.class)
                .single();
        return count > 0;
    }
    
    public Product save(Product product) {
        if (product.getId() == null) {
            Long id = jdbcClient.sql("INSERT INTO products (product_id, title, description, vendor, product_type, image_url, price) " +
                              "VALUES (:productId, :title, :description, :vendor, :productType, :imageUrl, :price) " +
                              "RETURNING id")
                    .param("productId", product.getProductId())
                    .param("title", product.getTitle())
                    .param("description", product.getDescription())
                    .param("vendor", product.getVendor())
                    .param("productType", product.getProductType())
                    .param("imageUrl", product.getImageUrl())
                    .param("price", product.getPrice())
                    .query(Long.class)
                    .single();
            product.setId(id);
        } else {
            jdbcClient.sql("UPDATE products SET product_id = :productId, title = :title, description = :description, " +
                           "vendor = :vendor, product_type = :productType, image_url = :imageUrl, price = :price " +
                           "WHERE id = :id")
                    .param("id", product.getId())
                    .param("productId", product.getProductId())
                    .param("title", product.getTitle())
                    .param("description", product.getDescription())
                    .param("vendor", product.getVendor())
                    .param("productType", product.getProductType())
                    .param("imageUrl", product.getImageUrl())
                    .param("price", product.getPrice())
                    .update();
        }
        return product;
    }
    
    public void deleteById(Long id) {
        jdbcClient.sql("DELETE FROM products WHERE id = :id")
                .param("id", id)
                .update();
    }
}
