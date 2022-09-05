package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.Cargo;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;

public interface EventProcessorStrategy {
    void process(Cargo cargo);

    boolean supports(EventType eventType);
}
