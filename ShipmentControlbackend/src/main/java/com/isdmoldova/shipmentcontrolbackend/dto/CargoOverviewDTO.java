package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;

public class CargoOverviewDTO {

    private String trackingNumber;

    private String destination;

    private CargoStatus cargoStatus;
}
