package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
    @JsonProperty("id")
    private String productId;
    
    private String title;
    private String body_html;
    private String vendor;
    private String product_type;
    private List<ImageDto> images;
    private List<VariantDto> variants;
    
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

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }

    public List<VariantDto> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantDto> variants) {
        this.variants = variants;
    }
    
    public String getMainImageUrl() {
        if (images != null && !images.isEmpty()) {
            return images.get(0).getSrc();
        }
        return null;
    }
    
    public BigDecimal getPrice() {
        if (variants != null && !variants.isEmpty()) {
            return variants.get(0).getPriceAsBigDecimal();
        }
        return BigDecimal.ZERO;
    }
}
