
package com.isdmoldova.shipmentcontrolbackend.controller;

import com.isdmoldova.shipmentcontrolbackend.payload.request.LoginCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.request.SignupCommand;
import com.isdmoldova.shipmentcontrolbackend.payload.response.JWTTokenSuccessResponse;
import com.isdmoldova.shipmentcontrolbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
    @PostMapping("/signup")
    public ResponseEntity<Void> registerUser(@Validated @RequestBody SignupCommand signupCommand) {
        authService.createUser(signupCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}




