package com.isdmoldova.shipmentcontrolbackend.entity.enums;

import lombok.Getter;

@Getter
public enum CargoStatus {

    ANALYZING ("analyzing"),
    PREPARING("preparing") ,
    DELIVERED("delivered"),
    IN_ROUTE("in-route"),
    ARRIVED("arrived"),
    UNLOADED_DESTINATION("unloaded_at_destination");

    private final String label;

    CargoStatus(String label) {
        this.label = label;
    }


}
