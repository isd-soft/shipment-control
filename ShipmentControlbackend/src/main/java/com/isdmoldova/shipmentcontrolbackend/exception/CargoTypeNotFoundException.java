package com.isdmoldova.shipmentcontrolbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CargoTypeNotFoundException extends RuntimeException{
    public CargoTypeNotFoundException(String message) {
        super(message);
    }
}
