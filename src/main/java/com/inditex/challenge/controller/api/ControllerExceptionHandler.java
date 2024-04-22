package com.inditex.challenge.controller.api;

import com.inditex.challenge.dto.ResponseDTO;
import com.inditex.challenge.exceptions.PriceNotFoundException;
import com.inditex.challenge.constants.ResponseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerExceptionHandler {


    private final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseDTO> handleWalletNotFoundException(PriceNotFoundException e) {
        log.error("Wallet not found: Reason: {}", e.getMessage());
        return ResponseHandler.response(
                ResponseConstants.PRICE_NOT_FOUND.getStatus(),
                ResponseConstants.PRICE_NOT_FOUND.getMessage(),
                HttpStatus.NOT_FOUND);
    }

}
