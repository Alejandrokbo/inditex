package com.inditex.challenge.constants;

import lombok.Getter;

@Getter
public enum ResponseConstants {
    /**
     * Custom OK Responses
     */
    OK("OK", "All good"),

    /**
     * Custom error for responses
     */
    E400("BAD_REQUEST", "The request is not valid. Check the parameters."),
    E404("NOT_FOUND", "This content doesn't exist in this path."),
    E500("GATEWAY_ERROR", "Something was wrong.");

    private final String status;
    private final String message;

    ResponseConstants(String status, String message) {
        this.status = status;
        this.message = message;
    }

}