package com.isdmoldova.shipmentcontrolbackend.payload.request;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LegCommand {

    @NotEmpty(message = "Please enter the country")
    private String country;

    @NotEmpty(message = "Please enter the country code")
    private String countryCode;

    @NotEmpty(message = "Please enter the address")
    private String address;

    @NotEmpty(message = "Please enter the name")
    private String name;

   // @NotEmpty(message = "Please enter the price")
    private Double price;

}
