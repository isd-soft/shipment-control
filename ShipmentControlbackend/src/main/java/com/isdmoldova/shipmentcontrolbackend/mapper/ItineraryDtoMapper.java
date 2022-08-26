package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.ItineraryDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.LegDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.RouteDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.UserDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;
import com.isdmoldova.shipmentcontrolbackend.entity.Leg;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class ItineraryDtoMapper {

    private final LegDtoMapper legDtoMapper;
    public ItineraryDTO map(Itinerary itinerary) {

        ItineraryDTO itineraryDTO = new ItineraryDTO();

        itineraryDTO.setLegDTOS(itinerary.getLegs()
                .stream().map(legDtoMapper::map)
                .collect(Collectors.toList()));

        itineraryDTO.setExecutionTime(itinerary.getDaysOfExecution());

        return itineraryDTO;
    }
}
