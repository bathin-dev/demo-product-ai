package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public String getProductsPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "products/index";
    }
    
    @GetMapping("/list")
    public String getProductsList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/fragments/product-list :: productList";
    }
    
    @PostMapping
    public String addProduct(@Valid @ModelAttribute("newProduct") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.getAllProducts());
            return "products/fragments/product-form :: productForm";
        }
        
        productService.saveProduct(product);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("newProduct", new Product());
        return "products/fragments/product-list :: productList";
    }
    
    @GetMapping("/fetch")
    public String fetchProducts(Model model) {
        productService.fetchAndSaveProducts();
        model.addAttribute("products", productService.getAllProducts());
        return "products/fragments/product-list :: productList";
    }
}
