package com.sam.banking_system.controller;

import com.sam.banking_system.dto.UserDto;
import com.sam.banking_system.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PatchMapping("/password/{userId}")
    public ResponseEntity<String> updatePassword(@PathVariable Long userId,@RequestBody String password){
        return new ResponseEntity<>(userService.updatePassword(userId,password), HttpStatus.OK);
    }
}
