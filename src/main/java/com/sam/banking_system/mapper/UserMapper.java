package com.sam.banking_system.mapper;

import com.sam.banking_system.dto.CreateUserDto;
import com.sam.banking_system.dto.UserDto;
import com.sam.banking_system.model.Role;
import com.sam.banking_system.model.User;

public class UserMapper {

    public static User dtoToEntity(CreateUserDto createUserDto){
        User user = new User();
        user.setRole(Role.CUSTOMER); //every new user is assigned CUSTOMER role
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());
        user.setLastName(createUserDto.getLastName());
        user.setFirstName(createUserDto.getFirstName());
        return user;
    }

    public static UserDto entityToDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
