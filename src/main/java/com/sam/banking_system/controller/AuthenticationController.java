package com.sam.banking_system.controller;

import com.sam.banking_system.dto.*;
import com.sam.banking_system.service.AuthenticationService;
import com.sam.banking_system.service.BlacklistedTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AuthenticationController class that handles authentication and authorization requests.
 *
 * @author LS Khiba
 * @version 1.0
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final BlacklistedTokenService blacklistedTokenService;
    private final AuthenticationService authenticationService;

    /**
     * Constructor that initializes the dependencies.
     * @param blacklistedTokenService the Blacklisted Token Service
     */
    public AuthenticationController(BlacklistedTokenService blacklistedTokenService, AuthenticationService authenticationService) {
        this.blacklistedTokenService = blacklistedTokenService;
        this.authenticationService = authenticationService;
    }

    /**
     * Handles login requests and returns a JWT token.
     *
     * @param loginRequest the login request
     * @return the JWT token response
     * @throws Exception if an error occurs
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.OK);
    }

    /**
     * Handles signup requests and creates a new user.
     *
     * @param signupRequest the user data transfer object
     * @return the created user
     */
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody SignupRequest signupRequest) {
        return new ResponseEntity<>(authenticationService.signup(signupRequest),HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String tokenHear){
        if(tokenHear!=null && tokenHear.startsWith("Bearer ")){
            String token = tokenHear.substring(7);
            blacklistedTokenService.blacklistToken(token);
        }
        return new ResponseEntity<>("Logged out successfully.",HttpStatus.OK);
    }
}