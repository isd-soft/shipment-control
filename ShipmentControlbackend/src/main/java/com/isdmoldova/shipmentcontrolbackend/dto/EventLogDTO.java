package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import lombok.Data;

@Data
public class EventLogDTO {


    private String trackingNumber;

    private EventType eventType;

    private CargoStatus cargoStatus;


}
