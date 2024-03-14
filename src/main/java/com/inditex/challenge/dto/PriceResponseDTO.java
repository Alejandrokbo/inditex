package com.inditex.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PriceResponseDTO {

    @JsonProperty("PRODUCT_ID")
    private Integer productId;

    @JsonProperty("BRAND_ID")
    private Integer brandId;

    @JsonProperty("PRICE")
    private Double price;

    @JsonProperty("CURR")
    private String currency;

    @JsonProperty("START_DATE")
    private String startDate;

    @JsonProperty("END_DATE")
    private String endDate;
}
