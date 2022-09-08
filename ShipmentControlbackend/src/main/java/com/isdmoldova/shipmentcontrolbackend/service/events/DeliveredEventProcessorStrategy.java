package com.isdmoldova.shipmentcontrolbackend.service.events;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import com.isdmoldova.shipmentcontrolbackend.service.EventProcessorStrategy;
import org.springframework.stereotype.Component;

@Component
public class DeliveredEventProcessorStrategy implements EventProcessorStrategy {
    @Override
    public void process(Cargo cargo) {
        cargo.setCargoStatus(CargoStatus.DELIVERED);
    }

    @Override
    public boolean supports(EventType eventType) {
        return (eventType == EventType.DELIVERED);
    }
}
