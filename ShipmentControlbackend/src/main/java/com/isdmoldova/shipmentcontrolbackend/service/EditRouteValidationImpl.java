package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.payload.request.RouteCommand;
import org.springframework.stereotype.Service;

@Service
public class EditRouteValidationImpl implements EditRouteValidation {

    @Override
    public void validate(RouteCommand command) {
        validateTransport(command);
        validateLeg(command);
    }

    private void validateTransport(RouteCommand routeCommand) {
        if (routeCommand.getTransportIdList().isEmpty())
            throw new ValidationException("transportList", "The transport list cannot be empty!");

    }

    public void validateLeg(RouteCommand routeCommand) {
        if (routeCommand.getItineraryCommand().getLegList().isEmpty())
            throw new ValidationException("legList", "The leg list cannot be empty!");
    }
}
