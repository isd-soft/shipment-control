package com.isdmoldova.shipmentcontrolbackend.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    GOODS_COMPANY("Goods Company"),
    SHIPMENT_COMPANY("Shipment_company");

    private final String description;
}
