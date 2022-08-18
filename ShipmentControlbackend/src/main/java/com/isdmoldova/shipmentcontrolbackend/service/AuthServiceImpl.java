package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.entity.Role;
import com.isdmoldova.shipmentcontrolbackend.entity.User;
import com.isdmoldova.shipmentcontrolbackend.entity.enums.UserType;
import com.isdmoldova.shipmentcontrolbackend.exception.UserExistException;
import com.isdmoldova.shipmentcontrolbackend.payload.request.LoginCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.SignupCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.response.JWTTokenSuccessResponse;
import com.isdmoldova.shipmentcontrolbackend.repository.RoleRepository;
import com.isdmoldova.shipmentcontrolbackend.repository.UserRepository;
import com.isdmoldova.shipmentcontrolbackend.security.jwt.JWTAuthenticationFilter;
import com.isdmoldova.shipmentcontrolbackend.security.jwt.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private static final String GOODS_COMPANY_ROLE = "GOODS_COMPANY";
    private static final String SHIPMENT_COMPANY_ROLE = "SHIPMENT_COMPANY";


    @Override
    public void createUser(SignupCommand userIn) {
        Optional<User> userByUsername = userRepository.findUserByUsername(userIn.getUserName());
        if (userByUsername.isPresent()) {
            throw new UserExistException("The user " + userIn.getUserName() + " already exist. Please check credentials");
        }
        Role role = getUserRole(userIn);
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setUsername(userIn.getUserName());
        user.setCompanyName(userIn.getCompanyName());
        user.setTelephoneNumber(userIn.getTelephoneNumber());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.addRole(role);
        log.info("Saving User {}", userIn.getEmail());
        userRepository.save(user);
    }

    @Override
    public JWTTokenSuccessResponse authenticateUser(LoginCommand loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JWTAuthenticationFilter.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return new JWTTokenSuccessResponse(true, jwt);
    }

    private Role getUserRole(SignupCommand signupCommand) {
        if (signupCommand.getUserType() == UserType.GOODS_COMPANY) {
            return roleRepository.findByName(GOODS_COMPANY_ROLE)
                    .orElseThrow();
        }
        return roleRepository.findByName(SHIPMENT_COMPANY_ROLE)
                .orElseThrow();
    }
}

