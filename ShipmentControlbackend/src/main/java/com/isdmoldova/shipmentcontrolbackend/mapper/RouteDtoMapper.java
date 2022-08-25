package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.Transport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class RouteDtoMapper {

    private final TransportDtoMapper transportDtoMapper ;
    public RouteDTO map(Route route) {

        final RouteDTO routeDTO = new RouteDTO();

        routeDTO.setRouteId(route.getId());
        routeDTO.setUserId(route.getUser().getId());
        routeDTO.setTransportDTOList(route.getTransports()
                .stream().map(transportDtoMapper::map).collect(Collectors.toList()));
        routeDTO.setAvailableDaysRentList(route.getAvailableDaysRent()
                .stream().collect(Collectors.toList()));
        routeDTO.setMaxLoadVolume(route.getMaxLoadVolume());
        routeDTO.setMaximalLoadWeight(route.getMaximalLoadValue());
        routeDTO.setEstimatedAmountTimeShipment(route.getEstimatedDays());
        routeDTO.setItineraryDTO(routeDTO.getItineraryDTO());

        return routeDTO;
    }
}
