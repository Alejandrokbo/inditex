package com.inditex.challenge.Repository;

import com.inditex.challenge.constants.Currency;
import com.inditex.challenge.model.Brand;
import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import com.inditex.challenge.repository.PriceRepository;
import com.inditex.challenge.repository.ProductRepository;
import com.inditex.challenge.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PriceRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceRepository priceRepository;

    DateUtils dateUtils = new DateUtils();

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
        price.setStartDate(dateUtils.stringToDate("2020-06-14-10.00.00"));
        price.setEndDate(dateUtils.stringToDate("2020-12-31-23.59.59"));
        price.setPrice(35.50);
        price.setCurrency(Currency.EUR.getValue());
        price.setPriority(0);

        Price priceWithHigherPriority = new Price();
        priceWithHigherPriority.setProduct(product);
        priceWithHigherPriority.setStartDate(dateUtils.stringToDate("2020-06-14-00.00.00"));
        priceWithHigherPriority.setEndDate(dateUtils.stringToDate("2020-06-14-20.00.00"));
        priceWithHigherPriority.setPrice(25.45);
        priceWithHigherPriority.setCurrency(Currency.EUR.getValue());
        priceWithHigherPriority.setPriority(1);

        Price priceWithDifferentDate = new Price();
        priceWithDifferentDate.setProduct(product);
        priceWithDifferentDate.setStartDate(dateUtils.stringToDate("2020-06-14-15.00.00"));
        priceWithDifferentDate.setEndDate(dateUtils.stringToDate("2020-07-14-20.00.00"));
        priceWithDifferentDate.setPrice(30.00);
        priceWithDifferentDate.setCurrency(Currency.EUR.getValue());
        priceWithDifferentDate.setPriority(1);

        List<Price> prices = new ArrayList<>();
        prices.add(price);
        prices.add(priceWithHigherPriority);
        prices.add(priceWithDifferentDate);

        product.setPrices(prices);

        Product result = productRepository.save(product);
        assertNotNull(result);
        assertEquals(3, result.getPrices().size());
    }
}
