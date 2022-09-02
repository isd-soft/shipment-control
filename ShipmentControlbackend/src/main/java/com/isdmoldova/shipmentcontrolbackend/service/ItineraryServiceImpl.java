package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.ItineraryDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;
import com.isdmoldova.shipmentcontrolbackend.mapper.ItineraryDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.repository.ItineraryRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final RouteRepository routeRepository;

    private final ItineraryDtoMapper itineraryDtoMapper;

    public Optional<Itinerary> findItineraryById(Long id) {
        return itineraryRepository.findItineraryById(id);
    }

    @Override
    public ItineraryDTO findByRouteId(Long routeId) {
        final var route = routeRepository.findById(routeId)
                .orElseThrow();
        return itineraryRepository.findByRoute(route)
                .map(itineraryDtoMapper::map)
                .orElseThrow();
    }
}

