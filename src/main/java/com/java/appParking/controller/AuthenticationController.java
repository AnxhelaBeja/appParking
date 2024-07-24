package com.java.appParking.controller;

import com.java.appParking.model.AuthenticationResponse;
import com.java.appParking.model.dto.ClientRegistrationRequest;
import com.java.appParking.model.dto.LoginRequest;
import com.java.appParking.model.dto.UserRegistrationRequest;
import com.java.appParking.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRegistrationRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/register/client")
    public ResponseEntity<AuthenticationResponse> registerClient(
            @RequestBody ClientRegistrationRequest request) {

        return ResponseEntity.ok(authService.registerClient(request));
    }

}


