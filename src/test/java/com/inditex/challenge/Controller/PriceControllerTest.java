package com.inditex.challenge.Controller;

import com.inditex.challenge.controller.PriceController;
import com.inditex.challenge.dto.PriceResponseDTO;
import com.inditex.challenge.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.Objects;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {

    Logger logger = Logger.getLogger(PriceControllerTest.class.getName());

    @Autowired
    private PriceController priceController;

    @Test
    void contextLoads() {
        assertThat(priceController).isNotNull();
    }

    /**
     * @Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void givenTime_10_date_14_andBrand_1_thenResultIsPrice1() throws ParseException {
        ResponseEntity<ResponseDTO> result = priceController.getPrice(35455, "2020-06-14-10.00.00", 1);
        assertThat(result).isNotNull();

        logger.info("Response: " + result.toString());

        PriceResponseDTO price = (PriceResponseDTO) Objects.requireNonNull(result.getBody()).data;
        assertEquals(35.50, price.getPrice());
    }

    /**
     * @Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void givenTime_16_date_14_andBrand_1_thenResultIsPrice2() throws ParseException {
        ResponseEntity<ResponseDTO> result = priceController.getPrice(35455, "2020-06-14-16.00.00", 1);
        assertThat(result).isNotNull();

        logger.info("Response: " + result.toString());


        PriceResponseDTO price = (PriceResponseDTO) Objects.requireNonNull(result.getBody()).data;
        assertEquals(25.45, price.getPrice());
    }

    /**
     * @Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void givenTime_21_date_14_andBrand_1_thenResultIsPrice1() throws ParseException {
        ResponseEntity<ResponseDTO> result = priceController.getPrice(35455, "2020-06-14-21.00.00", 1);
        assertThat(result).isNotNull();

        logger.info("Response: " + result.toString());

        PriceResponseDTO price = (PriceResponseDTO) Objects.requireNonNull(result.getBody()).data;
        assertEquals(35.50, price.getPrice());
    }

    /**
     * @Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void givenTime_10_date_15_andBrand_1_thenResultIsPrice3() throws ParseException {
        ResponseEntity<ResponseDTO> result = priceController.getPrice(35455, "2020-06-15-10.00.00", 1);
        assertThat(result).isNotNull();

        logger.info("Response: " + result.toString());

        PriceResponseDTO price = (PriceResponseDTO) Objects.requireNonNull(result.getBody()).data;
        assertEquals(30.50, price.getPrice());
    }

    /**
     * @Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void givenTime_21_date_16_andBrand_1_thenResultIsPrice2() throws ParseException {
        ResponseEntity<ResponseDTO> result = priceController.getPrice(35455, "2020-06-15-21.00.00", 1);
        assertThat(result).isNotNull();

        logger.info("Response: " + result.toString());

        PriceResponseDTO price = (PriceResponseDTO) Objects.requireNonNull(result.getBody()).data;
        assertEquals(38.95, price.getPrice());
    }


}