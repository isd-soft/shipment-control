package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportType;
import com.isdmoldova.shipmentcontrolbackend.payload.request.ItineraryCommand;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RouteDTO {

    private Long userId;
    private Long routeId;
    private List<TransportDTO> transportDTOList;
    private ItineraryDTO itineraryDTO;
    private List<AvailableDaysRent> availableDaysRentList;
    private Double maximalLoadWeight;
    private Double maxLoadVolume;
    private Long estimatedAmountTimeShipment;

}
