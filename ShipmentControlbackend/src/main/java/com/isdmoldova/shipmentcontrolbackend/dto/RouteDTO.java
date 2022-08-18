package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;
import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.AvailableDaysRent;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoType;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.TransportType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.rsocket.server.RSocketServer;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RouteDTO {

    private User user;

    private Itinerary itinerary;

    private List<Transport> transports;

    private CargoType cargoType;

    private String detailedRouteDescription;

    private String estimatedDays;

    private String origin;

    private String destination;

    private List<AvailableDaysRent> availableDaysRent;

    private LocalTime estimatedAmountTimeShipment;

    private Double maximalLoadValue;
}
