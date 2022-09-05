package com.isdmoldova.shipmentcontrolbackend.mapper;


import com.isdmoldova.shipmentcontrolbackend.dto.EventLogDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.EventLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class EventLogDtoMapper {

    public EventLogDTO eventLogDTO(EventLog eventLog){

        EventLogDTO eventLogDTO = new EventLogDTO();

        eventLogDTO.setTrackingNumber(eventLog.getTrackingNumber());
        eventLogDTO.setEventType(eventLog.getEventType());
        eventLogDTO.setCargoStatus(eventLog.getCargoStatus());

        return eventLogDTO;

    }

}
