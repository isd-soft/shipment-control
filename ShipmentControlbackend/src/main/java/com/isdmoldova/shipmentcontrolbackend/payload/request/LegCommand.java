package com.isdmoldova.shipmentcontrolbackend.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LegCommand {
    private String country;
    private String countryCode;
    private String address;
    private String name;
}
