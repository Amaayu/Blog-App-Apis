package com.aashuwithcode.blogappapis.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aashuwithcode.blogappapis.payloads.UserDto;
import com.aashuwithcode.blogappapis.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // post creat user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/user/{id}")
    public UserDto getUser(@PathVariable String id) {
        return userService.getUserById(Integer.parseInt(id));
    }

    @DeleteMapping("/users/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(Integer.parseInt(id));
    }

}
