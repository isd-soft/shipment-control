package com.isdmoldova.shipmentcontrolbackend.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginCommand {

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;

}
