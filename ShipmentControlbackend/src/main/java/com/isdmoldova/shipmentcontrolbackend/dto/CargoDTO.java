package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoDTO {


    private String trackingNumber;

    private Route route;

    private Double totalVolume;

    private TransportationType transportationType;

    private String destination;

    private CargoStatus cargoStatus;
}
