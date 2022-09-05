package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CargoStatus;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "event_log")
@Data
public class EventLog extends BaseEntity{

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargoStatus")
    private CargoStatus cargoStatus;

}
