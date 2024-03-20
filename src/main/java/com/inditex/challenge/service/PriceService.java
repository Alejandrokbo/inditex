package com.inditex.challenge.service;

import com.inditex.challenge.model.Price;

import java.text.ParseException;

public interface PriceService {

    Price getPriceWithHighestPriority(Integer product, String date) throws ParseException;
}
