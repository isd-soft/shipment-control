package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import org.springframework.stereotype.Component;

@Component
public class RouteDtoMapper {

    public RouteDTO map(Route route) {
        final RouteDTO routeDTO = new RouteDTO();
        routeDTO.setDetailedRouteDescription(route.getDetailedRouteDescription());
        routeDTO.setDestination(route.getDestination());
        routeDTO.setEstimatedDays(route.getEstimatedDays());
        routeDTO.setItinerary(route.getItinerary());
        routeDTO.setEstimatedAmountTimeShipment(route.getEstimatedAmountTimeShipment());
        routeDTO.setOrigin(route.getOrigin());
        routeDTO.setMaximalLoadValue(route.getMaximalLoadValue());
        routeDTO.setTransportationType((route.getTransportType()));
        routeDTO.setUser(route.getUser());
        routeDTO.setAvailableDaysRent(route.getAvailableDaysRent());

        return routeDTO;
    }
}
