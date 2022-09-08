package com.isdmoldova.shipmentcontrolbackend.mapper;


import com.isdmoldova.shipmentcontrolbackend.dto.EventLogDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.EventLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class EventLogDtoMapper {

    public EventLogDTO map(EventLog eventLog){

        EventLogDTO eventLogDTO = new EventLogDTO();

        eventLogDTO.setCreatedAt(eventLog.getCreatedAt());
        eventLogDTO.setEventType(eventLog.getEventType());
        eventLogDTO.setCargoStatus(eventLog.getCargoStatus());
        eventLogDTO.setLeg(eventLog.getLeg());

        return eventLogDTO;

    }

}
