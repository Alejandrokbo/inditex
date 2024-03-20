package com.inditex.challenge.Repository;

import com.inditex.challenge.model.Brand;
import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import com.inditex.challenge.repository.BrandRepository;
import com.inditex.challenge.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(properties = "spring.sql.init.data-locations=classpath:/no-data.sql")
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @Autowired
    protected BrandRepository brandRepository;

    @Test
    void findProductByProductIdAndBrandBrandIdTest() {
        Brand brand = new Brand();
        brand.setBrandId(1);
        brand.setBrandName("ZARA");
        brandRepository.save(brand);

        Product product = new Product();
        product.setProductId(35455);
        product.setProductName("Product 1");
        product.setBrand(brand);

        List<Price> prices = new ArrayList<>();
        prices.add(new Price());
        prices.add(new Price());
        prices.add(new Price());

        product.setPrices(prices);

        when(productRepository.save(product)).thenReturn(product);

        assertNotNull(productRepository.save(product));

        when(productRepository.existsByProductIdAndBrandBrandId(35455, 1)).thenReturn(true);
        assertTrue(productRepository.existsByProductIdAndBrandBrandId(35455, 1));
    }
}
