package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.UserDTO;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.mapper.UserDtoMapper;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Override
    public UserDTO findUserByUsername(String username){
        final User user = userRepository.findUserByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found!"));
        return userDtoMapper.map(user);
    }

    @Override
    public String findEmailByUsername(String username) {
        return userRepository.findEmailByCompanyName(username);
    }
}
