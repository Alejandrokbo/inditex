package com.inditex.challenge.service;

import com.inditex.challenge.exceptions.PriceNotFoundException;
import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import com.inditex.challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.inditex.challenge.utils.DateUtils.stringToDate;

@Service
public class PriceServiceImp implements PriceService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Price getPriceWithHighestPriority(Integer productId, String date) throws ParseException, PriceNotFoundException {
        Product product = productRepository.getReferenceById(productId);
        Date dateToFind = stringToDate(date);
        List<Price> prices = productRepository.findByDateInRange(dateToFind, product);

        return prices.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .orElseThrow(PriceNotFoundException::new);
    }
}
