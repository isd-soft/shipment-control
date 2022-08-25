package com.isdmoldova.shipmentcontrolbackend.exception;

public class CargoOverviewNotFoundException extends RuntimeException{

    public CargoOverviewNotFoundException( String message) {
        super(message);
    }
}
