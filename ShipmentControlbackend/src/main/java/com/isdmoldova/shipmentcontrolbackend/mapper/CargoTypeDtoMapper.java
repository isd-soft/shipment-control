package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;

public class CargoTypeDtoMapper {

    public CargoTypeDTO map(CargoType cargoType) {
        final CargoTypeDTO cargoTypeDTO = new CargoTypeDTO();
        cargoType.setCargoList(cargoType.getCargoList());
        cargoType.setTransportType(cargoType.getTransportType());
        cargoType.setMaxWeight(cargoType.getMaxWeight());
        cargoType.setName(cargoType.getName());

        return cargoTypeDTO;
    }
}
