package com.isdmoldova.shipmentcontrolbackend.dto;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CargoDTO {

    private Long id;
    private String trackingNumber;
    private Double totalVolume;
    private Double totalWeight;
    private String origin;
    private String destination;
    private CargoStatus cargoStatus;
    private ItineraryDTO itineraryDTO;
    private List<CargoTypeDTO> cargoTypes;
    private LocalDate bookingDate;

}
