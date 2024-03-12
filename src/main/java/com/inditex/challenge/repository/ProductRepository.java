package com.inditex.challenge.repository;

import com.inditex.challenge.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<Product, Long> {
}
