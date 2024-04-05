package com.inditex.challenge.controller.api;


import com.inditex.challenge.constants.ResponseConstants;
import com.inditex.challenge.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "STATUS")
@RestController
@RequestMapping("/status")
public class ServerStatus {
    @Operation(summary = "Check server status", description = "Check if the server is online")
    @GetMapping(produces = "application/json")
    public ResponseEntity<ResponseDTO> hello() {
        return ResponseHandler.response(
                ResponseConstants.OK.getStatus(),
                ResponseConstants.OK.getMessage(),
                HttpStatus.OK);
    }
}
