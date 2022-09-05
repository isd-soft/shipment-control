package com.isdmoldova.shipmentcontrolbackend.entity.enums;

import lombok.Getter;

@Getter
public enum CargoStatus {

    ANALYZING ("analyzing"),
    PREPARING("preparing") ,
    DELIVERED("delivered"),
    IN_ROUTE("in-route"),
    UNLOADED_DESTINATION("unloaded_at_destination");

    private String label;

    CargoStatus(String label) {
        this.label = label;
    }


}
