package com.isdmoldova.shipmentcontrolbackend.dto;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CargoDTO {

    private String trackingNumber;
    private Double totalVolume;
    private Double totalWeight;
    private CargoStatus cargoStatus;
    private List<CargoTypeDTO> cargoTypes;
    private ItineraryDTO itineraryDTO;
    private LocalDate bookingDate;


}
