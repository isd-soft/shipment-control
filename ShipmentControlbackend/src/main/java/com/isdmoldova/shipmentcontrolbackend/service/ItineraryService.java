package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;
import java.util.Optional;

public interface ItineraryService {

    Optional<Itinerary> findItineraryById(Long id);
}
