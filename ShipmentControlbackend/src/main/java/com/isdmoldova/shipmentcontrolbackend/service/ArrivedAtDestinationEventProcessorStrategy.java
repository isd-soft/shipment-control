package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ArrivedAtDestinationEventProcessorStrategy implements EventProcessorStrategy{

    @Override
    public void process(Cargo cargo) {
        cargo.setCurrentLeg(cargo.getDestination());
        cargo.setCargoStatus(CargoStatus.UNLOADED_DESTINATION);
    }

    @Override
    public boolean supports(EventType eventType) {
        return eventType == EventType.ARRIVED_AT_DESTINATION;
    }
}
