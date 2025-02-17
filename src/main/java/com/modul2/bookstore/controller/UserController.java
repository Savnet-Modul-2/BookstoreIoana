package com.modul2.bookstore.controller;

import com.modul2.bookstore.dto.UserDTO;
import com.modul2.bookstore.entities.User;
import com.modul2.bookstore.mapper.UserMapper;
import com.modul2.bookstore.repository.UserRepository;
import com.modul2.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        User userToCreate = UserMapper.userDTO2User(userDTO);
        User createdUser = userService.create(userToCreate);
        return ResponseEntity.ok(UserMapper.user2UserDTO(createdUser));
    }

    @PutMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody UserDTO updatedUserDTO) {
        User userToUpdate = UserMapper.userDTO2User(updatedUserDTO);
        User updatedUser = userService.verify(userToUpdate.getEmail(), userToUpdate.getVerificationCode());
        return ResponseEntity.ok(UserMapper.user2UserDTO(updatedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO updatedUserDTO) {
        User userToLogin = UserMapper.userDTO2User(updatedUserDTO);
        User loggedinUser = userService.login(userToLogin.getEmail(), userToLogin.getPassword());
        return ResponseEntity.ok(UserMapper.user2UserDTO(loggedinUser));
    }
}
