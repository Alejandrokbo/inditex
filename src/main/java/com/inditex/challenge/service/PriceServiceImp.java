package com.inditex.challenge.service;

import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import com.inditex.challenge.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.inditex.challenge.utils.DateUtils.stringToDate;

@Service
public class PriceServiceImp implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImp(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPriceWithHighestPriority(Product product, String date) throws ParseException {
        Date dateToFind = stringToDate(date);
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
