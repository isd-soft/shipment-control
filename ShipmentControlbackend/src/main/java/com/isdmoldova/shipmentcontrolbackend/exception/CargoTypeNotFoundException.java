package com.isdmoldova.shipmentcontrolbackend.exception;

public class CargoTypeNotFoundException extends RuntimeException{
    public CargoTypeNotFoundException(String message) {
        super(message);
    }
}
