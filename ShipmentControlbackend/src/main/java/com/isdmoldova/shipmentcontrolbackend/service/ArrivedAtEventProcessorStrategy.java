package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import org.springframework.stereotype.Component;

@Component
class ArrivedAtEventProcessorStrategy implements EventProcessorStrategy {

    @Override
    public void process(Cargo cargo) {
        int currentLegIndex = cargo.getItinerary().getLegs().indexOf(cargo.getCurrentLeg());
        if (currentLegIndex != cargo.getItinerary().getLegs().size() - 1) {
            cargo.setCurrentLeg(cargo.getItinerary().getLegs().get(currentLegIndex + 1));
        }
    }

    @Override
    public boolean supports(EventType eventType) {
        return eventType == EventType.ARRIVED_AT;
    }
}
