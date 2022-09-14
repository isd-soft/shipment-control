package com.isdmoldova.shipmentcontrolbackend.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RouteDTO {

    private Long userId;
    private Long routeId;
    private List<TransportDTO> transportDTOList;
    private ItineraryDTO itineraryDTO;
    private List<AvailableDaysRentDTO> availableDaysRentList;
    private Double maximalLoadWeight;
    private Double maxLoadVolume;
    private Long estimatedAmountTimeShipment;
    private String routeDescription;
    private String providerCompany;

}
