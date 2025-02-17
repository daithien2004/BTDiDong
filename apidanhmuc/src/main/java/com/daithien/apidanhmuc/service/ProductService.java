package com.daithien.apidanhmuc.service;

import com.daithien.apidanhmuc.entity.Category;
import com.daithien.apidanhmuc.entity.Product;
import com.daithien.apidanhmuc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.sql.Timestamp;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.findByCategory(new Category(categoryId));
    }

    public List<Product> getTop10NewProducts() {
        Timestamp sevenDaysAgo = Timestamp.from(Instant.now().minus(7, ChronoUnit.DAYS));
        return productRepository.findTop10ByCreatedAtAfter(sevenDaysAgo, PageRequest.of(0, 10));
    }

    public List<Object[]> getTop10BestSellingProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        return productRepository.findTop10BestSellingProducts(pageable);
    }
}
