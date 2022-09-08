package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.EventLogDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;

import java.util.List;

public interface EventService {

    void processEvent(String trackingNumber, EventType eventType);

    List<EventLogDTO> findAllEventsByTrackNumber(String trackingNumber);



}
