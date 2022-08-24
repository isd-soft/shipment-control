package com.isdmoldova.shipmentcontrolbackend.util;

import com.isdmoldova.shipmentcontrolbackend.controller.TransportController;
import com.isdmoldova.shipmentcontrolbackend.exception.CargoTypeNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.exception.TransportNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.exception.UserNotAllowedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({
            TransportNotFoundException.class,
            UserNotAllowedException.class,
            CargoTypeNotFoundException.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(Map.of("error:", ex.getMessage()));
    }
}
