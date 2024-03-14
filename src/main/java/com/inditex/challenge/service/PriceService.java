package com.inditex.challenge.service;

import com.inditex.challenge.model.Price;
import com.inditex.challenge.model.Product;

import java.util.List;

public interface PriceService {

    Price getPrice(Product product, String date);
}
