package com.sam.banking_system.controller;

import com.sam.banking_system.dto.AuthenticationRequest;
import com.sam.banking_system.dto.AuthenticationResponse;
import com.sam.banking_system.dto.CreateUserDto;
import com.sam.banking_system.dto.UserDto;
import com.sam.banking_system.service.BlacklistedTokenService;
import com.sam.banking_system.service.CustomUserDetailsService;
import com.sam.banking_system.security.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final BlacklistedTokenService blacklistedTokenService;
    private final UserController userController;

    /**
     * Constructor that initializes the dependencies.
     *
     * @param authenticationManager   the authentication manager
     * @param jwtTokenUtil            the JWT token utility
     * @param customUserDetailsService the custom user details service
     * @param blacklistedTokenService the Blacklisted Token Service
     */
    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, CustomUserDetailsService customUserDetailsService, BlacklistedTokenService blacklistedTokenService, UserController userController) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.blacklistedTokenService = blacklistedTokenService;
        this.userController = userController;
    }

    /**
     * Handles login requests and returns a JWT token.
     *
     * @param loginRequest the login request
     * @return the JWT token response
     * @throws Exception if an error occurs
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid credentials");
        }
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());

        AuthenticationResponse jwtResponse = new AuthenticationResponse(jwtTokenUtil.generateToken(userDetails));
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    /**
     * Handles signup requests and creates a new user.
     *
     * @param createUserDto the user data transfer object
     * @return the created user
     */
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody CreateUserDto createUserDto) {
        return userController.createUser(createUserDto);
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