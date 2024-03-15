package com.inditex.challenge.Service;

import com.inditex.challenge.constants.BrandConstants;
import com.inditex.challenge.constants.Currency;
import com.inditex.challenge.model.Brand;
import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import com.inditex.challenge.repository.PriceRepository;
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
import static org.mockito.Mockito.when;


@SpringBootTest
public class PriceServiceTest {
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImp priceService;

    @Test
    public void getPriceWithDateAndHighPriority() throws ParseException {
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

        Price expectedPrice2 = new Price();
        expectedPrice2.setPrice(21.50);
        expectedPrice2.setCurrency(Currency.EUR.getValue());
        expectedPrice2.setStartDate(stringToDate("2024-04-14-08.00.00"));
        expectedPrice2.setEndDate(stringToDate("2024-04-14-20.00.00"));
        expectedPrice2.setPriority(2);

        prices.add(expectedPrice1);
        prices.add(expectedPrice2);

        when(priceRepository.findAllByProductId(product)).thenReturn(prices);

        // Call to method getPriceWithHighestPriority() of PriceService with the mocked data
        Price expectedPrice = priceService.getPriceWithHighestPriority(product, "2024-04-13-10.00.00");

        // Assert the result
        assertEquals(expectedPrice.getPrice(), expectedPrice1.getPrice());
        assertEquals(expectedPrice.getCurrency(), expectedPrice1.getCurrency());
    }


}
