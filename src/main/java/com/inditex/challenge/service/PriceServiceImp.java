package com.inditex.challenge.service;

import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import com.inditex.challenge.repository.PriceRepository;
import com.inditex.challenge.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class PriceServiceImp implements PriceService {

    private final PriceRepository priceRepository;

    private final DateUtils dateUtils = new DateUtils();

    public PriceServiceImp(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPriceWithHighestPriority(Product product, String date) throws ParseException {
        Date dateToFind = dateUtils.stringToDate(date);
        List<Price> prices = priceRepository.findAllByProductId(product);

        return prices.stream()
                .filter(price -> {
                    Date start = price.getStartDate();
                    Date end = price.getEndDate();
                    return start.before(dateToFind) && end.after(dateToFind);
                })
                .max(Comparator.comparingInt(Price::getPriority))
                .orElse(null);
    }
}
