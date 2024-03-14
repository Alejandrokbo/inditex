package com.inditex.challenge.controller.api;


import com.inditex.challenge.constants.ResponseConstants;
import com.inditex.challenge.dto.ResponseMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/status")
public class ServerStatus {
    @ResponseBody
    @GetMapping("")
    public ResponseEntity<ResponseMessageDTO> hello() {
        return ResponseHandler.response(
                ResponseConstants.OK.getStatus(),
                ResponseConstants.OK.getMessage(),
                HttpStatus.OK);
    }
}
