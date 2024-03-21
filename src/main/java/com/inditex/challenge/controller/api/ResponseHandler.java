package com.inditex.challenge.controller.api;

import com.inditex.challenge.dto.ResponseDTO;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    @NotNull
    @Contract("_, _, _, -> new")
    public static ResponseEntity<ResponseDTO> response(String statusMessage, String message, HttpStatus status) {
        var resp = new ResponseDTO();
        resp.message = message;
        resp.statusMessage = statusMessage;
        return new ResponseEntity<>(resp, defaultHeaders(), status);
    }

    @NotNull
    @Contract("_, _, _, _ -> new")
    public static ResponseEntity<ResponseDTO> response(String statusMessage, String message, HttpStatus status, Object responseObj) {
        var resp = new ResponseDTO();
        resp.data = responseObj;
        resp.message = message;
        resp.statusMessage = statusMessage;
        return new ResponseEntity<>(resp, defaultHeaders(), status);
    }

    @NotNull
    private static HttpHeaders defaultHeaders() {
        var headers = new HttpHeaders();
        headers.set("Content-Type", String.valueOf(MediaType.APPLICATION_JSON));
        return headers;
    }

}
