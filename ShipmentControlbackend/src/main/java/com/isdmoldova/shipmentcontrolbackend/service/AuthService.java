package com.isdmoldova.shipmentcontrolbackend.service;


import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.payload.request.LoginCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.SignupCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.response.JWTTokenSuccessResponse;



public interface AuthService {

    void createUser(SignupCommand userIn);

    JWTTokenSuccessResponse authenticateUser(LoginCommand loginRequest);


}