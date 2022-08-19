package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.Leg;
import com.isdmoldova.shipmentcontrolbackend.entity.Route;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ItineraryDTO {

    private Route route;

    private List<Leg> leg;

    private LocalDateTime executionTime;

}