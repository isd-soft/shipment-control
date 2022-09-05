package com.isdmoldova.shipmentcontrolbackend.entity.enums;

import lombok.Getter;

@Getter
public enum EventType {
    LOADED("loaded"),
    ARRIVED_AT("arrived-at"),
    ARRIVED_AT_DESTINATION("arrived-at-dest"),
    UNLOADED_AT_DESTINATION("unloaded-at-dest"),
    DEPARTED_FROM("departed-from"),
    DELIVERED("delivered");
    private final String label;

    EventType(String label) {
        this.label = label;
    }

}
