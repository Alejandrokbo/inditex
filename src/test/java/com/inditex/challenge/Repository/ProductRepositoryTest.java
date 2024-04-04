package com.inditex.challenge.Repository;

import com.inditex.challenge.constants.Currency;
import com.inditex.challenge.model.Brand;
import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import com.inditex.challenge.repository.BrandRepository;
import com.inditex.challenge.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.inditex.challenge.utils.DateUtils.stringToDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
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

        assertNotNull(when(productRepository.save(product)).thenReturn(product));

        when(productRepository.existsByProductIdAndBrandBrandId(35455, 1)).thenReturn(true);
        assertTrue(productRepository.existsByProductIdAndBrandBrandId(35455, 1));
    }

    @Test
    void getPriceBetweenDatesTest() throws ParseException {

        Brand brand = new Brand();
        brand.setBrandId(1);
        brand.setBrandName("ZARA");


        Product product = new Product();
        product.setProductId(35455);
        product.setProductName("Product 1");
        product.setBrand(brand);

        Price price = new Price();
        price.setProduct(product);
        price.setStartDate(stringToDate("2020-06-14-10.00.00"));
        price.setEndDate(stringToDate("2020-12-31-23.59.59"));
        price.setPrice(35.50);
        price.setCurrency(Currency.EUR.getValue());
        price.setPriority(0);

        Price priceWithHigherPriority = new Price();
        priceWithHigherPriority.setProduct(product);
        priceWithHigherPriority.setStartDate(stringToDate("2020-06-14-00.00.00"));
        priceWithHigherPriority.setEndDate(stringToDate("2020-06-14-20.00.00"));
        priceWithHigherPriority.setPrice(25.45);
        priceWithHigherPriority.setCurrency(Currency.EUR.getValue());
        priceWithHigherPriority.setPriority(1);

        Price priceWithDifferentDate = new Price();
        priceWithDifferentDate.setProduct(product);
        priceWithDifferentDate.setStartDate(stringToDate("2020-06-14-15.00.00"));
        priceWithDifferentDate.setEndDate(stringToDate("2020-07-14-20.00.00"));
        priceWithDifferentDate.setPrice(30.00);
        priceWithDifferentDate.setCurrency(Currency.EUR.getValue());
        priceWithDifferentDate.setPriority(1);

        List<Price> prices = new ArrayList<>();
        prices.add(price);
        prices.add(priceWithHigherPriority);
        prices.add(priceWithDifferentDate);

        when(productRepository.findByDateInRange(stringToDate("2020-06-14-10.00.00"), product)).thenReturn(prices);
        assertEquals(3, prices.size());
    }
}
