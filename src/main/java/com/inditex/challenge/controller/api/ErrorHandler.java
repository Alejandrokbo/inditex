package com.inditex.challenge.controller.api;


import com.inditex.challenge.constants.ResponseConstants;
import com.inditex.challenge.dto.ResponseDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandler implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ResponseDTO> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        @NotNull ResponseEntity<ResponseDTO> resp = ResponseHandler.response(
                ResponseConstants.OK.getStatus(),
                ResponseConstants.OK.getMessage(),
                HttpStatus.OK
        );

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                resp = ResponseHandler.response(
                        ResponseConstants.E404.getStatus(),
                        ResponseConstants.E404.getMessage(),
                        HttpStatus.NOT_FOUND);
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                resp = ResponseHandler.response(
                        ResponseConstants.E500.getStatus(),
                        ResponseConstants.E500.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                resp = ResponseHandler.response(
                        ResponseConstants.E400.getStatus(),
                        ResponseConstants.E400.getMessage(),
                        HttpStatus.BAD_REQUEST);
            } else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                resp = ResponseHandler.response(
                        ResponseConstants.E500.getStatus(),
                        ResponseConstants.E500.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR);

            }
        }
        return resp;
    }

}
