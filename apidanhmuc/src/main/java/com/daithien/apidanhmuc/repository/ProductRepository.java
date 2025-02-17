package com.daithien.apidanhmuc.repository;

import com.daithien.apidanhmuc.entity.Category;
import com.daithien.apidanhmuc.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import java.sql.Timestamp;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p ORDER BY p.createdAt DESC")
    List<Product> findTop10ByCreatedAtAfter(@Param("date") Timestamp date, Pageable pageable);

    @Query("SELECT od.product, SUM(od.quantity) AS totalSold " +
            "FROM OrderDetail od GROUP BY od.product ORDER BY totalSold DESC")
    List<Object[]> findTop10BestSellingProducts(Pageable pageable);
}
