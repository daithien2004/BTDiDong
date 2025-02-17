package com.daithien.apidanhmuc.controller;

import com.daithien.apidanhmuc.entity.Product;
import com.daithien.apidanhmuc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @GetMapping("/top10-new")
    public ResponseEntity<List<Product>> getTop10NewProducts() {
        return ResponseEntity.ok(productService.getTop10NewProducts());
    }

    @GetMapping("/top10-best-selling")
    public ResponseEntity<List<Object[]>> getTop10BestSellingProducts() {
        return ResponseEntity.ok(productService.getTop10BestSellingProducts());
    }
}
