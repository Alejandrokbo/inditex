package com.inditex.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseMessageDTO {
    @JsonProperty("status")
    public String statusMessage;
    @JsonProperty("message")
    public String message;
}