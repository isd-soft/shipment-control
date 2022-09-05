package com.isdmoldova.shipmentcontrolbackend.entity.enums;

import lombok.Getter;

@Getter
public enum CargoStatus {

    ANALYZING ("analyzing"),
    PREPARING("preparing") ,
    DELIVERED("delivered");

    private String label;

    CargoStatus(String label) {
        this.label = label;
    }


}
