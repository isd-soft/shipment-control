package com.isdmoldova.shipmentcontrolbackend.security;


import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Username not found with username:" + username));
        return new UserCustomDetail(user);
    }




}