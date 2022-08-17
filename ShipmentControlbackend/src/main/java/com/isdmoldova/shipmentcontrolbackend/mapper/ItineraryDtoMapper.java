package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.ItineraryDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.UserDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ItineraryDtoMapper {

    public ItineraryDTO map(Itinerary itinerary) {
        final ItineraryDTO itineraryDTO = new ItineraryDTO();
        itineraryDTO.setRoute(itinerary.getRoute());
        itineraryDTO.setLegs(itinerary.getLegs());
        itineraryDTO.setExecutionTime(itinerary.getExecutionTime());

        return itineraryDTO;
    }
}
