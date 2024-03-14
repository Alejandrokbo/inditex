package com.inditex.challenge.constants;

import lombok.Getter;

@Getter
public enum Currency {

    EUR("EUR");

    private final String value;

    Currency(String value) {
        this.value = value;
    }

}
