package com.isdmoldova.shipmentcontrolbackend.mapper;

import com.isdmoldova.shipmentcontrolbackend.dto.UserDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserDTO map(User user) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCompanyName(user.getCompanyName());
        userDTO.setTelephoneNumber(user.getTelephoneNumber());

        return userDTO;
    }
}
