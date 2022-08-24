package com.isdmoldova.shipmentcontrolbackend.dto;

import lombok.Setter;
import lombok.Getter;

import java.time.LocalDateTime;

@Setter
@Getter

public class CargoTypeDTO {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
