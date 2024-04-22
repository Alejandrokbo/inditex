package com.inditex.challenge.Service;

import com.inditex.challenge.constants.BrandConstants;
import com.inditex.challenge.constants.Currency;
import com.inditex.challenge.exceptions.PriceNotFoundException;
import com.inditex.challenge.model.Brand;
import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import com.inditex.challenge.repository.ProductRepository;
import com.inditex.challenge.service.PriceServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.inditex.challenge.utils.DateUtils.stringToDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest
public class PriceServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PriceServiceImp priceService;

    @Test
    public void getPriceWithDateAndHighPriority() throws ParseException, PriceNotFoundException {
        Brand brand = new Brand();
        brand.setBrandId(1);
        brand.setBrandName(BrandConstants.ZARA.name());

        // Creating a Product object
        Product product = new Product();
        product.setProductId(35455);
        product.setBrand(brand);

        // Creating a list of Price objects
        List<Price> prices = new ArrayList<>();

        // Creating a Price test object
        Price expectedPrice1 = new Price();
        expectedPrice1.setPrice(20.50);
        expectedPrice1.setCurrency(Currency.EUR.getValue());
        expectedPrice1.setStartDate(stringToDate("2024-04-13-00.00.00"));
        expectedPrice1.setEndDate(stringToDate("2024-04-13-18.00.00"));
        expectedPrice1.setPriority(1);

        Price expectedPriceCorrect = new Price();
        expectedPriceCorrect.setPrice(21.50);
        expectedPriceCorrect.setCurrency(Currency.EUR.getValue());
        expectedPriceCorrect.setStartDate(stringToDate("2024-04-14-08.00.00"));
        expectedPriceCorrect.setEndDate(stringToDate("2024-04-14-20.00.00"));
        expectedPriceCorrect.setPriority(2);

        prices.add(expectedPrice1);
        prices.add(expectedPriceCorrect);
        product.setPrices(prices);

        String dateToFind = "2024-04-13-10.00.00";
        when(productRepository.findByDateInRange(stringToDate(dateToFind), product)).thenReturn(prices);
        when(productRepository.getReferenceById(35455)).thenReturn(product);
        // Call to method getPriceWithHighestPriority() of PriceService with the mocked data
        Price expectedPrice = priceService.getPriceWithHighestPriority(product.getProductId(), dateToFind);

        // Assert the result
        assertEquals(expectedPrice.getPrice(), expectedPriceCorrect.getPrice());
        assertEquals(expectedPrice.getCurrency(), expectedPriceCorrect.getCurrency());
    }

    @Test
    public void get_expected_exception_for_price_not_found() throws ParseException {
        Brand brand = new Brand();
        brand.setBrandId(1);
        brand.setBrandName(BrandConstants.ZARA.name());

        // Creating a Product object
        Product product = new Product();
        product.setProductId(35455);
        product.setBrand(brand);

        // Creating a list of Price objects
        List<Price> prices = new ArrayList<>();

        // Creating a Price test object
        Price expectedPrice1 = new Price();
        expectedPrice1.setPrice(20.50);
        expectedPrice1.setCurrency(Currency.EUR.getValue());
        expectedPrice1.setStartDate(stringToDate("2024-04-13-00.00.00"));
        expectedPrice1.setEndDate(stringToDate("2024-04-13-18.00.00"));
        expectedPrice1.setPriority(1);

        prices.add(expectedPrice1);
        product.setPrices(prices);

        String dateToFind = "2024-04-14-20.00.00";
        // Call to method getPriceWithHighestPriority() of PriceService with the mocked data
        assertThrows(PriceNotFoundException.class, () -> priceService.getPriceWithHighestPriority(product.getProductId(), dateToFind));
    }

}
