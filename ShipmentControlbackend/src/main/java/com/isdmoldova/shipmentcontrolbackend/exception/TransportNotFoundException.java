package com.isdmoldova.shipmentcontrolbackend.exception;

public class TransportNotFoundException extends RuntimeException {

    public TransportNotFoundException(String message) {
        super(message);
    }

}
