package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import org.springframework.stereotype.Component;

@Component
public class CargoDtoMapper {

    public CargoDTO map(Cargo cargo) {
        final CargoDTO cargoDTO = new CargoDTO();
      //  cargoDTO.setRoute(cargo.getRoute());
        cargoDTO.setCargoStatus(cargo.getCargoStatus());
      //  cargoDTO.setDestination(cargo.getDestination());
        cargoDTO.setTrackingNumber(cargo.getTrackingNumber());
        cargoDTO.setTotalVolume(cargo.getTotalVolume());
        cargoDTO.setTotalWeight(cargo.getTotalWeight());

        return cargoDTO;
    }
}
