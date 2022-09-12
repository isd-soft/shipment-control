package com.isdmoldova.shipmentcontrolbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ItineraryDTO {

    private List<LegDTO> legDTOS;
    private Long executionTime;

}
