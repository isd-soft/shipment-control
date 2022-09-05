package com.isdmoldova.shipmentcontrolbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EventTypeNotFoundException extends RuntimeException {

    public EventTypeNotFoundException(String message) {
        super(message);
    }
}
