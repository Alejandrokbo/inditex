package com.inditex.challenge.controller;

import com.inditex.challenge.constants.ResponseConstants;
import com.inditex.challenge.controller.api.ResponseHandler;
import com.inditex.challenge.dto.PriceResponseDTO;
import com.inditex.challenge.dto.ResponseDTO;
import com.inditex.challenge.exceptions.PriceNotFoundException;
import com.inditex.challenge.exceptions.ProductNotFoundException;
import com.inditex.challenge.model.Price;
import com.inditex.challenge.service.PriceServiceImp;
import com.inditex.challenge.service.ProductServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

import static com.inditex.challenge.utils.DateUtils.dateToString;

@Tag(name = "PRICE", description = "Operations related to prices")
@RestController
@RequestMapping("/price")
public class PriceController {

    Logger log = LoggerFactory.getLogger(PriceController.class);

    @Autowired
    private PriceServiceImp priceService;

    @Autowired
    private ProductServiceImp productService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get a price.",
            description = "Get the price of a product given a date and a brand",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Price found"),
                    @ApiResponse(responseCode = "404", description = "Price not found"),
            })
    @GetMapping("/{productId}/{date}/{brand}")
    public ResponseEntity<ResponseDTO> getPrice(@PathVariable("productId") Integer productId, @PathVariable("date") String date, @PathVariable("brand") Integer brand) throws ParseException, PriceNotFoundException, ProductNotFoundException {
        log.warn("Looking for the existence of the product with id: {}", productId);
        if (!productService.existsByProductIdAndBrandBrandId(productId, brand)) {
            throw new ProductNotFoundException();
        }

        Price resultPrice = priceService.getPriceWithHighestPriority(productId, date);

        PriceResponseDTO result = new PriceResponseDTO();
        result.setProductId(resultPrice.getProduct().getProductId());
        result.setBrandId(resultPrice.getProduct().getBrand().getBrandId());
        result.setPrice(resultPrice.getPrice());
        result.setCurrency(resultPrice.getCurrency());
        result.setStartDate(resultPrice.getStartDate());
        result.setEndDate(resultPrice.getEndDate());
        return ResponseHandler.response(ResponseConstants.OK.getStatus(), "Price for product: " + productId, HttpStatus.OK, result);
    }

}
