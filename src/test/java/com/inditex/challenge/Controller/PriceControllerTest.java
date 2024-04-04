package com.inditex.challenge.Controller;

import com.inditex.challenge.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void givenTime_10_date_14_andBrand_1_thenResultIsPrice1() {
        assertThat(this.restTemplate.getForObject(String.format("http://localhost:%d/price/", port) + "35455/2020-06-14-10.00.00/1", ResponseDTO.class).toString()).contains("35.5");
    }

    /**
     * Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void givenTime_16_date_14_andBrand_1_thenResultIsPrice2() {
        assertThat(this.restTemplate.getForObject(String.format("http://localhost:%d/price/", port) + "35455/2020-06-14-16.00.00/1", ResponseDTO.class).toString()).contains("25.45");
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void givenTime_21_date_14_andBrand_1_thenResultIsPrice1() {
        assertThat(this.restTemplate.getForObject(String.format("http://localhost:%d/price/", port) + "35455/2020-06-14-21.00.00/1", ResponseDTO.class).toString()).contains("35.5");
    }

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void givenTime_10_date_15_andBrand_1_thenResultIsPrice3() {
        assertThat(this.restTemplate.getForObject(String.format("http://localhost:%d/price/", port) + "35455/2020-06-15-10.00.00/1", ResponseDTO.class).toString()).contains("30.5");
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void givenTime_21_date_16_andBrand_1_thenResultIsPrice2() {
        assertThat(this.restTemplate.getForObject(String.format("http://localhost:%d/price/", port) + "35455/2020-06-15-21.00.00/1", ResponseDTO.class).toString()).contains("38.95");
    }


}