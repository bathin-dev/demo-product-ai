package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class Product {
    
    private Long id;
    private String productId;
    
    @NotBlank(message = "Product title is required")
    private String title;
    private String description;
    private String vendor;
    private String productType;
    private String imageUrl;
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;
    
    public Product() {
    }

    public Product(String productId, String title, String description, String vendor,
                 String productType, String imageUrl, BigDecimal price) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.vendor = vendor;
        this.productType = productType;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", title='" + title + '\'' +
                ", vendor='" + vendor + '\'' +
                ", productType='" + productType + '\'' +
                ", price=" + price +
                '}';
    }
}
