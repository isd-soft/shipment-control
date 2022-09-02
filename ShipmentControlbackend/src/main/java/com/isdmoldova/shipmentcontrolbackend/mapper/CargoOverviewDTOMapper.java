package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.CargoOverviewDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import org.springframework.stereotype.Component;

@Component
public class CargoOverviewDTOMapper {

    public CargoOverviewDTO map(Cargo cargo) {
        final CargoOverviewDTO cargoOverviewDTO = new CargoOverviewDTO();

        cargoOverviewDTO.setCargoStatus(cargo.getCargoStatus());


        cargoOverviewDTO.setTrackingNumber(cargo.getTrackingNumber());

        return cargoOverviewDTO;
    }

}


