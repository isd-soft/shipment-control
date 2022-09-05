package com.isdmoldova.shipmentcontrolbackend.service.events;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import com.isdmoldova.shipmentcontrolbackend.service.EventProcessorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ArrivedAtDestinationEventProcessorStrategy implements EventProcessorStrategy {

    @Override
    public void process(Cargo cargo) {
        cargo.setCurrentLeg(cargo.getDestination());
    }

    @Override
    public boolean supports(EventType eventType) {
        return eventType == EventType.ARRIVED_AT_DESTINATION;
    }
}
