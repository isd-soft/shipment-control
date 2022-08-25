package com.isdmoldova.shipmentcontrolbackend.payload.request;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoOverviewCommand {

    private  String trackingNumber;

    private  String destination;

    private CargoStatus cargoStatus;
}
