package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransportTypeDTO {

    private String name;

    private List<CargoType> cargoTypeList;

    private List<Route> routeList;
}
