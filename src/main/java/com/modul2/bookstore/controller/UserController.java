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
    @PostMapping("/verify")
    public ResponseEntity<?> verifyAccount(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");

        if (email == null || code == null) {
            return ResponseEntity.badRequest().body("Email and code are required.");
        }

        Optional<User> optionalUser = userService.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getVerificationCode().equals(code)) {
                user.setVerifiedAccount(true);
                user.setVerificationCode(null);
                userRepository.save(user);
                return ResponseEntity.ok("Account verified successfully!");
            }
            return ResponseEntity.badRequest().body("Invalid verification code.");
        }
        return ResponseEntity.badRequest().body("User not found.");
    }

}
