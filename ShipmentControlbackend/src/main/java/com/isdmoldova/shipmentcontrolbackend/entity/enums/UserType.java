package com.isdmoldova.shipmentcontrolbackend.entity.enums;

import lombok.Getter;

@Getter
public enum UserType {

    GOODS_COMPANY("Goods Company"),
    SHIPMENT_COMPANY("Shipment_company");

    private String description;

    UserType(String description) {
        this.description = description;
    }
}
