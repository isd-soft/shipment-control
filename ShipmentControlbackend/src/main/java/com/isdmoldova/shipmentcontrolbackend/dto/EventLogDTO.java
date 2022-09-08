package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventLogDTO {


    private LocalDateTime createdAt;

    private EventType eventType;

    private CargoStatus cargoStatus;

    private String leg;


}
