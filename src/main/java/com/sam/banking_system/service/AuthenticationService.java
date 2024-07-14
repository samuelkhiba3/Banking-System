package com.sam.banking_system.service;

import com.sam.banking_system.dto.LoginRequest;
import com.sam.banking_system.dto.AuthenticationResponse;
import com.sam.banking_system.dto.SignupRequest;
import com.sam.banking_system.dto.UserDto;
import com.sam.banking_system.mapper.UserMapper;
import com.sam.banking_system.model.User;
import com.sam.banking_system.security.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthenticationService(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtil jwtTokenUtil, PasswordEncoder passwordEncoder, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid credentials");
        }
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        UserDto userDto = UserMapper.entityToDto((User) userDetails);
        return new AuthenticationResponse(jwtTokenUtil.generateToken(userDetails),userDto);
    }

    public AuthenticationResponse signup(SignupRequest signupRequest) {
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        User user = UserMapper.dtoToEntity(signupRequest);
        String token = jwtTokenUtil.generateToken(user);
        return new AuthenticationResponse(token,userService.createUser(user));
    }


}
