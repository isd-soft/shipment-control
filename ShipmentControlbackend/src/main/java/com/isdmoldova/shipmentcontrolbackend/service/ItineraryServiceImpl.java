package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;
import com.isdmoldova.shipmentcontrolbackend.repository.ItineraryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItineraryServiceImpl implements ItineraryService{

    private final ItineraryRepository itineraryRepository;

        public ItineraryServiceImpl(final ItineraryRepository itineraryRepository){
            this.itineraryRepository  = itineraryRepository;
        }

        public Optional<Itinerary> findItineraryById(Long id){
            return itineraryRepository.findItineraryById(id);
        }
    }

