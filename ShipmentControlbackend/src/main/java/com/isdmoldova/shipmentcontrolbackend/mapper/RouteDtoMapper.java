package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.ItineraryDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.TransportDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class RouteDtoMapper {

    private final TransportDtoMapper transportDtoMapper;
    private final ItineraryDtoMapper itineraryDtoMapper;
    private final AvailableDaysRentDtoMapper availableDaysRentDtoMapper;

    public RouteDTO map(Route route) {

        final RouteDTO routeDTO = new RouteDTO();

        routeDTO.setUserId(route.getUser().getId());
        routeDTO.setRouteId(route.getId());
        routeDTO.setTransportDTOList(route.getTransports()
                .stream().map(transportDtoMapper::map)
                .collect(Collectors.toList()));
        routeDTO.setItineraryDTO(itineraryDtoMapper.map(route.getItinerary()));
        routeDTO.setAvailableDaysRentList(route.getAvailableDaysRent()
                .stream().map(availableDaysRentDtoMapper::map).collect(Collectors.toList()));
        routeDTO.setMaxLoadVolume(route.getMaxLoadVolume());
        routeDTO.setRouteDescription(route.getDetailedRouteDescription());
        routeDTO.setMaximalLoadWeight(route.getMaximalLoadValue());
        routeDTO.setEstimatedAmountTimeShipment(route.getItinerary().getDaysOfExecution());


        return routeDTO;
    }

}

