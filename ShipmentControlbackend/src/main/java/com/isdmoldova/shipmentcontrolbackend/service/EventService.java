package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;

public interface EventService {

     void processEvent(String trackingNumber, EventType eventType);

}
