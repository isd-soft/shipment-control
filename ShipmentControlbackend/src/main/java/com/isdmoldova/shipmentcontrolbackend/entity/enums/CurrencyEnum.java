package com.isdmoldova.shipmentcontrolbackend.entity.enums;

import lombok.Getter;

@Getter
public enum CurrencyEnum {

    EUR("Euro"),
    USD("Dollar"),
    GBP("Pound Sterling");

    private final String label;

    CurrencyEnum(String label) {
        this.label = label;
    }
}
