package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CargoDtoMapper {
    private final CargoTypeDtoMapper cargoTypeDtoMapper;
    private final ItineraryDtoMapper itineraryDtoMapper;

    public CargoDTO map(Cargo cargo) {
        final CargoDTO cargoDTO = new CargoDTO();


        cargoDTO.setCargoStatus(cargo.getCargoStatus());

        cargoDTO.setCargoStatus(cargo.getCargoStatus());


        cargoDTO.setId(cargo.getId());
        cargoDTO.setCargoStatus(cargo.getCargoStatus());
        cargoDTO.setOrigin(cargo.getOrigin().getAddress());
        cargoDTO.setDestination(cargo.getDestination().getAddress());
        cargoDTO.setTrackingNumber(cargo.getTrackingNumber());
        cargoDTO.setCargoTypes(cargo.getCargoTypes()
                .stream().map(cargoTypeDtoMapper::map).collect(Collectors.toList()));
        cargoDTO.setTotalVolume(cargo.getTotalVolume());
        cargoDTO.setCurrentLegId(cargo.getCurrentLeg().getId());
        cargoDTO.setItineraryDTO(itineraryDtoMapper.map(cargo.getItinerary()));
        cargoDTO.setTotalWeight(cargo.getTotalWeight());
        cargoDTO.setBookingDate(cargo.getBookingDate());
        return cargoDTO;
    }
}
