package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoTypeDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import org.springframework.stereotype.Component;

@Component
public class CargoTypeDtoMapper {
    public CargoTypeDTO map(CargoType cargo) {
        final CargoTypeDTO cargoTypeDTO = new CargoTypeDTO();
        cargoTypeDTO.setId(cargo.getId());
        cargoTypeDTO.setName(cargo.getName());
        cargoTypeDTO.setCreatedAt(cargo.getCreatedAt());
        cargoTypeDTO.setModifiedAt(cargo.getModifiedAt());
        return cargoTypeDTO;
    }
}
