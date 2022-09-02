package com.isdmoldova.shipmentcontrolbackend.payload.request;

import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CargoCommand {

    private Double totalVolume;
    private Double totalWeight;
    private List<Long> cargoTypeList;
    //private CargoStatus cargoStatus;
    private ItineraryCommand itineraryCommand;
}
