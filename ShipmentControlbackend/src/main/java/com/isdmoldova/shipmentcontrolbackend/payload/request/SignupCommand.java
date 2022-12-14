package com.isdmoldova.shipmentcontrolbackend.payload.request;

import com.isdmoldova.shipmentcontrolbackend.entity.enums.UserRole;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SignupCommand {

    private UserRole userRole;

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    private String email;

    @NotEmpty(message = "Please enter Company Name")
    private String companyName;

    @NotEmpty(message = "Please enter your username")
    private String userName;

    @NotEmpty(message = "Telephone number is required")
    private String telephoneNumber;

    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;

    private String confirmPassword;


}
