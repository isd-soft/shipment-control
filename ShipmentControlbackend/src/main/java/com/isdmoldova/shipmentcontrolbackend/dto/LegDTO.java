package com.isdmoldova.shipmentcontrolbackend.dto;

import com.isdmoldova.shipmentcontrolbackend.entity.Itinerary;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LegDTO {

    private String name;

    private String address;

    private Itinerary itinerary;

}
