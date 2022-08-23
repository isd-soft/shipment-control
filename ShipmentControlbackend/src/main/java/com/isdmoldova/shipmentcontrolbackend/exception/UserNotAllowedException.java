package com.isdmoldova.shipmentcontrolbackend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class UserNotAllowedException extends RuntimeException{
    public UserNotAllowedException(String message){
        super(message);
    }
}
