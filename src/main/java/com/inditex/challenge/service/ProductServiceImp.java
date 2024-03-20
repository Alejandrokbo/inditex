package com.inditex.challenge.service;

import com.inditex.challenge.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {

    final
    ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean existsByProductIdAndBrandBrandId(int productId, int brandId) {
        return productRepository.existsByProductIdAndBrandBrandId(productId, brandId);
    }
}
