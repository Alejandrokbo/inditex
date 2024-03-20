package com.inditex.challenge.service;

import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import com.inditex.challenge.repository.PriceRepository;
import com.inditex.challenge.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.inditex.challenge.utils.DateUtils.stringToDate;

@Service
public class PriceServiceImp implements PriceService {

    private final PriceRepository priceRepository;

    private final ProductRepository productRepository;

    public PriceServiceImp(PriceRepository priceRepository, ProductRepository productRepository) {
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Price getPriceWithHighestPriority(Integer productId, String date) throws ParseException {
        Product product = productRepository.getReferenceById(productId);
        Date dateToFind = stringToDate(date);
        List<Price> prices = priceRepository.findByDateInRange(dateToFind, product);

        return prices.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .orElse(null);
    }
}
