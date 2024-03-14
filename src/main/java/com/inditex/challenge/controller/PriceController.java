package com.inditex.challenge.controller;

import com.inditex.challenge.constants.ResponseConstants;
import com.inditex.challenge.controller.api.ResponseHandler;
import com.inditex.challenge.dto.PriceResponseDTO;
import com.inditex.challenge.dto.ResponseDataDTO;
import com.inditex.challenge.model.Price;
import com.inditex.challenge.repository.ProductRepository;
import com.inditex.challenge.service.PriceServiceImp;
import com.inditex.challenge.utils.DateUtils;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
@RequestMapping("/price")
public class PriceController {

    Logger log = LoggerFactory.getLogger(PriceController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceServiceImp priceService;

    DateUtils dateUtils = new DateUtils();

    @GetMapping("")
    public ResponseEntity<ResponseDataDTO> getPrice(@PathParam("productId") String productId, @PathParam("date") String date) throws ParseException {
        log.warn("Looking for the existence of the product with id: " + productId);
        if (!productRepository.existsById(Integer.parseInt(productId))) {
            return ResponseHandler.response(ResponseConstants.E404.getStatus(), "Product with id: " + productId + " does not exist", HttpStatus.NOT_FOUND, null);
        }

        Price resultPrice = priceService.getPrice(productRepository.getReferenceById(Integer.parseInt(productId)), date);

        PriceResponseDTO result = new PriceResponseDTO();
        result.setProductId(resultPrice.getProductId().getProductId());
        result.setBrandId(resultPrice.getProductId().getBrand().getBrandId());
        result.setPrice(resultPrice.getPrice());
        result.setCurrency(resultPrice.getCurrency());
        result.setStartDate(dateUtils.dateToString(resultPrice.getStartDate()));
        result.setEndDate(dateUtils.dateToString(resultPrice.getEndDate()));

        return ResponseHandler.response(ResponseConstants.OK.getStatus(), "Price for product", HttpStatus.OK, result);
    }

}
