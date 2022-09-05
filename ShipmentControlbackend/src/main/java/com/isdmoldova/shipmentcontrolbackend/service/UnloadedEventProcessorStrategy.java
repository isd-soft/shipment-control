package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import org.springframework.stereotype.Component;


import static com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus.DELIVERED;
import static com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType.UNLOADED_AT_DESTINATION;

@Component
public class UnloadedEventProcessorStrategy implements EventProcessorStrategy{

    @Override
    public void process(Cargo cargo) {
        cargo.setCurrentLeg(cargo.getDestination());
        cargo.setCargoStatus(DELIVERED);
    }

    @Override
    public boolean supports(EventType eventType) {
        return eventType == UNLOADED_AT_DESTINATION;
    }
}
