package com.isdmoldova.shipmentcontrolbackend.service.events;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.Leg;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import com.isdmoldova.shipmentcontrolbackend.service.EventProcessorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType.LOADED;

@Component
@Slf4j
class LoadedEventProcessorStrategy implements EventProcessorStrategy {

    @Override
    public void process(Cargo cargo) {
        cargo.setCurrentLeg(cargo.getOrigin());
        cargo.setCargoStatus(CargoStatus.IN_ROUTE);

    }

    @Override
    public boolean supports(EventType eventType) {
        return eventType == LOADED;
    }
}
