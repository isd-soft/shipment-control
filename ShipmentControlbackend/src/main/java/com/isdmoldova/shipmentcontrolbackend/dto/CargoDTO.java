package com.isdmoldova.shipmentcontrolbackend.dto;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CargoDTO {

    private Long id;
    private String trackingNumber;
    private Double totalVolume;
    private Double totalWeight;
    private String origin;
    private String destination;
    private Long currentLegId;
    private CargoStatus cargoStatus;
    private ItineraryDTO itineraryDTO;
    private List<CargoTypeDTO> cargoTypes;
    private LocalDate bookingDate;
    private String user;
    private String provider;
    private String userCompanyName;
    private String providerCompanyName;

}
