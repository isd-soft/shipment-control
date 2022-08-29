package com.isdmoldova.shipmentcontrolbackend.service;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private final String fieldName;
    private final String error;

    public ValidationException(String fieldName,
                               String error) {
        super(error);
        this.fieldName = fieldName;
        this.error = error;
    }
}
