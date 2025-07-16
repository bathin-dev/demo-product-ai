package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VariantDto {
    private String id;
    private String title;
    private String price;
    private String sku;
    private Integer inventory_quantity;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    public BigDecimal getPriceAsBigDecimal() {
        try {
            return new BigDecimal(price);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    public String getSku() {
        return sku;
    }
    
    public void setSku(String sku) {
        this.sku = sku;
    }
    
    public Integer getInventory_quantity() {
        return inventory_quantity;
    }
    
    public void setInventory_quantity(Integer inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }
}
