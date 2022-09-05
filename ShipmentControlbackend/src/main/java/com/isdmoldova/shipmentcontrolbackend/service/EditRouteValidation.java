package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;

public interface EditRouteValidation {

    void validate(RouteCommand command);
}
