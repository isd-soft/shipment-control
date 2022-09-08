package com.isdmoldova.shipmentcontrolbackend.service.events;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import com.isdmoldova.shipmentcontrolbackend.service.EventProcessorStrategy;
import org.springframework.stereotype.Component;

import static com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType.DEPARTED_FROM;

@Component
public class DepartedFromEventProcessorStrategy implements EventProcessorStrategy {
    @Override
    public void process(Cargo cargo) {

    }

    @Override
    public boolean supports(EventType eventType) {
        return (eventType == DEPARTED_FROM);
    }
}
