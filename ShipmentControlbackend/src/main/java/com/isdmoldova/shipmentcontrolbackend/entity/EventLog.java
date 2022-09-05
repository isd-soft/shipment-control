package com.isdmoldova.shipmentcontrolbackend.entity;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.EventType;

import javax.persistence.*;

@Entity
@Table(name = "event_log")
public class EventLog extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

    @Column(name = "status")
    private String status;

}
