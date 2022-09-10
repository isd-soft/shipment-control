package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.ItineraryDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

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
