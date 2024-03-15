package com.inditex.challenge.controller;

import com.inditex.challenge.constants.ResponseConstants;
import com.inditex.challenge.controller.api.ResponseHandler;
import com.inditex.challenge.dto.PriceResponseDTO;
import com.inditex.challenge.dto.ResponseDataDTO;
import com.inditex.challenge.model.Price;
import com.inditex.challenge.repository.ProductRepository;
import com.inditex.challenge.service.PriceServiceImp;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

import static com.inditex.challenge.utils.DateUtils.dateToString;

@Controller
@RequestMapping("/price")
public class PriceController {

    Logger log = LoggerFactory.getLogger(PriceController.class);

    private final ProductRepository productRepository;

    private final PriceServiceImp priceService;

    public PriceController(ProductRepository productRepository, PriceServiceImp priceService) {
        this.productRepository = productRepository;
        this.priceService = priceService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDataDTO> getPrice(@PathParam("productId") Integer productId, @PathParam("date") String date, @PathParam("Brand") Integer brand) throws ParseException {
        log.warn("Looking for the existence of the product with id: " + productId);
        if (!productRepository.existsByProductIdAndBrandBrandId(productId, brand)) {
            return ResponseHandler.response(ResponseConstants.E404.getStatus(), "Product with id: " + productId + " does not exist", HttpStatus.NOT_FOUND, null);
        }

        Price resultPrice = priceService.getPriceWithHighestPriority(productRepository.getReferenceById(productId), date);

        PriceResponseDTO result = new PriceResponseDTO();
        result.setProductId(resultPrice.getProduct().getProductId());
        result.setBrandId(resultPrice.getProduct().getBrand().getBrandId());
        result.setPrice(resultPrice.getPrice());
        result.setCurrency(resultPrice.getCurrency());
        result.setStartDate(dateToString(resultPrice.getStartDate()));
        result.setEndDate(dateToString(resultPrice.getEndDate()));

        return ResponseHandler.response(ResponseConstants.OK.getStatus(), "Price for product", HttpStatus.OK, result);
    }

}
