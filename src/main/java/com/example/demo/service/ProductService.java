package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductsResponse;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    
    @Value("${api.products.url}")
    private String apiUrl;
    
    @Autowired
    public ProductService(ProductRepository productRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB
                .build();
        this.webClient = WebClient.builder().exchangeStrategies(strategies).build();
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000, initialDelay = 0) // Run immediately at startup, then once a day
    public void fetchAndSaveProducts() {
        logger.info("Starting scheduled job to fetch products");
        
        try {
            String responseBody = webClient.get()
                    .uri(apiUrl)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            
            if (responseBody != null) {
                ProductsResponse response = objectMapper.readValue(responseBody, ProductsResponse.class);
                List<ProductDto> products = response.getProducts();
                
                if (products != null) {
                    // Limit to 50 products
                    products.stream()
                           .limit(50)
                           .forEach(this::saveProductFromDto);
                    
                    logger.info("Successfully fetched and saved products");
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching products from API: {}", e.getMessage(), e);
        }
    }
    
    private void saveProductFromDto(ProductDto dto) {
        try {
            if (productRepository.existsByProductId(dto.getProductId())) {
                logger.info("Product with ID {} already exists, skipping", dto.getProductId());
                return;
            }
            
            Product product = new Product();
            product.setProductId(dto.getProductId());
            product.setTitle(dto.getTitle());
            product.setDescription(dto.getBody_html());
            product.setVendor(dto.getVendor());
            product.setProductType(dto.getProduct_type());
            product.setImageUrl(dto.getMainImageUrl());
            product.setPrice(dto.getPrice());
            
            productRepository.save(product);
            logger.info("Saved product: {}", product.getTitle());
        } catch (Exception e) {
            logger.error("Error saving product {}: {}", dto.getTitle(), e.getMessage());
        }
    }
}
