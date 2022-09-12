package com.isdmoldova.shipmentcontrolbackend.dto;


import com.isdmoldova.shipmentcontrolbackend.entity.enums.CurrencyEnum;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LegDTO {

    private String name;
    private String address;
    private String country;
    private String countryCode;
    private Double price;
    private CurrencyEnum currency;
}
