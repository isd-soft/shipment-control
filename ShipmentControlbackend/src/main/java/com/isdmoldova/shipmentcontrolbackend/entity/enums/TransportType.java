package com.isdmoldova.shipmentcontrolbackend.entity.enums;

import lombok.Getter;

@Getter
public enum TransportType {

    ROAD_TRANSPORTATION("Road Transportation"),
    RAIL_TRANSPORTATION("Rail Transportation"),
    MARINE_TRANSPORTATION("Marine Transportation"),
    AIR_TRANSPORTATION("Air Transportation");

    private final String label;

    TransportType(String label) {
        this.label = label;
    }
}
