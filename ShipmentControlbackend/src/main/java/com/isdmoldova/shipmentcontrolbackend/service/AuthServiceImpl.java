package com.isdmoldova.shipmentcontrolbackend.service;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.exception.UserExistException;
import com.isdmoldova.shipmentcontrolbackend.payload.request.LoginCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.SignupCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.response.JWTTokenSuccessResponse;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import com.isdmoldova.shipmentcontrolbackend.security.UserCustomDetail;

import com.isdmoldova.shipmentcontrolbackend.security.jwt.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public void createUser(SignupCommand userIn) {
        Optional<User> userByUsername = userRepository.findUserByUsername(userIn.getUserName());
        if (userByUsername.isPresent()) {
            throw new UserExistException("The user " + userIn.getUserName() + " already exist. Please check credentials");
        }
        User user = new User();
        user.setRole(userIn.getUserRole());
        user.setEmail(userIn.getEmail());
        user.setUsername(userIn.getUserName());
        user.setCompanyName(userIn.getCompanyName());
        user.setTelephoneNumber(userIn.getTelephoneNumber());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        log.info("Saving User {}", userIn.getEmail());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public JWTTokenSuccessResponse authenticateUser(LoginCommand loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        UserCustomDetail userDetails = (UserCustomDetail) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JWTTokenSuccessResponse(
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getUsername(),
                roles,
                jwt
               );
    }
}



