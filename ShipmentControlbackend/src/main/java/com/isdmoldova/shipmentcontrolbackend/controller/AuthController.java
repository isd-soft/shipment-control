package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.payload.request.LoginCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.SignupCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.response.JWTTokenSuccessResponse;
import com.isdmoldova.shipmentcontrolbackend.service.AuthService;
import com.isdmoldova.shipmentcontrolbackend.util.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signin")
    public ResponseEntity<JWTTokenSuccessResponse> authenticateUser(@Validated @RequestBody LoginCommand loginCommand) {
        return new ResponseEntity<>(authService.authenticateUser(loginCommand), HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> customException(BadCredentialsException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("BAD_REQUEST");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> registerUser(@Validated @RequestBody SignupCommand signupCommand) {
        authService.createUser(signupCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}






