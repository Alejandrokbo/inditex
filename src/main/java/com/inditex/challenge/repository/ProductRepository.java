package com.inditex.challenge.repository;

import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByProductIdAndBrandBrandId(@NotNull Integer productId, @NotNull Integer brandId);

    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND :date BETWEEN p.startDate AND p.endDate")
    List<Price> findByDateInRange(@Param("date") Date date, @Param("productId") Product productId);
}
