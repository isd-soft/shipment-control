package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoOverviewDTO {

    private Long id;

    private String trackingNumber;

    private String origin;

    private String destination;

    private CargoStatus cargoStatus;
}
