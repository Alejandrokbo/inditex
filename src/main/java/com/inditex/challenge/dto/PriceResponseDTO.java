package com.inditex.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.inditex.challenge.utils.DateUtils.dateToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public void setStartDate(Date startDate) {
        this.startDate = dateToString(startDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = dateToString(endDate);
    }
}
