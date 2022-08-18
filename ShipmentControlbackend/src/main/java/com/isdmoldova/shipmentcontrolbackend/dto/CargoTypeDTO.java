package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.TransportType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CargoTypeDTO {

    private String name;

    private TransportType transportType;

    private Double maxWeight;

    private List<Cargo> cargoList;
}
