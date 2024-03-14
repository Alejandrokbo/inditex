package com.inditex.challenge.repository;

import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Integer> {
    List<Price> findAllByProductId(Product productId);

}
