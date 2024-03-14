package com.inditex.challenge.Repository;

import com.inditex.challenge.model.Brand;
import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import com.inditex.challenge.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(properties = "spring.sql.init.data-locations=classpath:/no-data.sql")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findProductByProductIdAndBrandBrandIdTest() {
        Brand brand = new Brand();
        brand.setBrandId(1);
        brand.setBrandName("ZARA");

        Product product = new Product();
        product.setProductId(35455);
        product.setProductName("Product 1");
        product.setBrand(brand);

        List<Price> prices = new ArrayList<>();
        prices.add(new Price());
        prices.add(new Price());
        prices.add(new Price());

        product.setPrices(prices);
        Product productResult = productRepository.save(product);
        assertNotNull(productResult);

        assertTrue(productRepository.existsByProductIdAndBrandBrandId(35455, 1));
    }
}
