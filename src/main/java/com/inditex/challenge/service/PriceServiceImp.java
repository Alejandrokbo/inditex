package com.inditex.challenge.service;

import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;
import com.inditex.challenge.repository.PriceRepository;
import com.inditex.challenge.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class PriceServiceImp implements PriceService{

    private final PriceRepository priceRepository;

    private final DateUtils dateUtils = new DateUtils();

    public PriceServiceImp(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPrice(Product product, String date) {
        List<Price> prices = priceRepository.findAllByProductId(product);
        Price result = null;
        try {
            for (Price price : prices) {
                if (dateUtils.stringToDate(date).after(price.getStartDate()) && dateUtils.stringToDate(date).before(price.getEndDate())) {
                    result = price;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
