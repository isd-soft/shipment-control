package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.UserDTO;

    public interface UserService {

        UserDTO findUserByUsername(String username);
    }

