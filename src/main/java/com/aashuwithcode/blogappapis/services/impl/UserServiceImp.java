package com.aashuwithcode.blogappapis.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aashuwithcode.blogappapis.entities.User;
import com.aashuwithcode.blogappapis.exceptions.ResourceNotFoundException;
import com.aashuwithcode.blogappapis.payloads.UserDto;
import com.aashuwithcode.blogappapis.repostonies.UserRepo;
import com.aashuwithcode.blogappapis.services.UserService;
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        this.userRepo.delete(user);

    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepo.findAll();

        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public UserDto getUserById(Integer userId) {

//        User user = this.userRepo.findById(userId);
        Optional<User> optional = userRepo.findById(userId);
        if(optional.isPresent()) {
            UserDto dto = new UserDto();
            User user = optional.get();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword());
            dto.setAbout(user.getAbout());
            return dto;
        }
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return null;

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        User updataUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updataUser);

        return userDto1;
    }

    private User dtoToUser(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        return user;

    }

    public UserDto userToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

}
