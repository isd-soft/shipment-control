package com.isdmoldova.shipmentcontrolbackend.util;

import com.isdmoldova.shipmentcontrolbackend.controller.TransportController;
import com.isdmoldova.shipmentcontrolbackend.exception.TransportNotFoundException;
import com.isdmoldova.shipmentcontrolbackend.exception.UserNotAllowedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackageClasses = {TransportController.class})
public class TransportExceptionHandler {

    @ExceptionHandler({
            TransportNotFoundException.class,
            UserNotAllowedException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(Exception ex) {
        final Map<String, String> errors = new HashMap<>();
        errors.put("validation", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
