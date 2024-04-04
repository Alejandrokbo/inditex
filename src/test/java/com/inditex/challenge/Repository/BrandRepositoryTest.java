package com.inditex.challenge.Repository;

import com.inditex.challenge.model.Brand;
import com.inditex.challenge.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestPropertySource(properties = "spring.sql.init.data-locations=classpath:/no-data.sql")
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testCreateBrand() {
        Brand brand = new Brand();
        brand.setBrandId(0);
        brand.setBrandName("PEPE");

        Brand brand1 = new Brand();
        brand1.setBrandName("Patricia");

        Brand result = brandRepository.save(brand);
        assertNotNull(result);
        assertEquals(1, result.getBrandId());
        assertEquals(brand.getBrandName(), result.getBrandName());

        Brand result1 = brandRepository.save(brand1);
        assertNotNull(result1);
        assertEquals(2, result1.getBrandId());
        assertEquals(brand1.getBrandName(), result1.getBrandName());


    }
}
