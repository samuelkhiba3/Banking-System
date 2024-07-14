package com.sam.banking_system.service;

import com.sam.banking_system.dto.UserDto;
import com.sam.banking_system.mapper.UserMapper;
import com.sam.banking_system.model.User;
import com.sam.banking_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto getUserById(Long userId){
        User user = userRepository.getReferenceById(userId);
        return UserMapper.entityToDto(user);
    }

    public UserDto createUser(User user){
        User savedUser = userRepository.save(user);
        return UserMapper.entityToDto(savedUser);
    }

    public String updatePassword(Long userId, String password) {
        User user = userRepository.getReferenceById(userId);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "Password successfully updated!";
    }
}
