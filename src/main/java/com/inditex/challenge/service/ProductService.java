package com.inditex.challenge.service;

public interface ProductService {

    boolean existsByProductIdAndBrandBrandId(int productId, int brandId);
}
