package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoOverviewDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import org.springframework.stereotype.Component;

@Component
public class CargoOverviewDTOMapper {

    public CargoOverviewDTO map(Cargo cargo) {
        final CargoOverviewDTO cargoOverviewDTO = new CargoOverviewDTO();

        cargoOverviewDTO.setId(cargo.getId());
        cargoOverviewDTO.setOrigin(cargo.getOrigin().getCountry());
        cargoOverviewDTO.setDestination(cargo.getDestination().getCountry());
        cargoOverviewDTO.setCargoStatus(cargo.getCargoStatus());
        cargoOverviewDTO.setTrackingNumber(cargo.getTrackingNumber());

        return cargoOverviewDTO;
    }

}


