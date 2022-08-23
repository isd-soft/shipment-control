package com.isdmoldova.shipmentcontrolbackend.exception;

public class UserNotAllowedException extends RuntimeException{
    public UserNotAllowedException(String message){
        super(message);
    }
}
