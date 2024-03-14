package com.inditex.challenge.service;

import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;

import java.text.ParseException;
import java.util.List;

public interface PriceService {

    Price getPriceWithHighestPriority(Product product, String date) throws ParseException;
}
