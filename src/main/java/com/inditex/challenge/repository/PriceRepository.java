package com.inditex.challenge.repository;

import com.inditex.challenge.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
