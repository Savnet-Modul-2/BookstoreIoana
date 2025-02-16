package com.modul2.bookstore.service;

import com.modul2.bookstore.entities.User;
import com.modul2.bookstore.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    public User create(User user) {
        String sha256Hex = DigestUtils.sha256Hex(user.getPassword()).toUpperCase();
        user.setPassword(sha256Hex);

        String verificationCode=String.valueOf(new Random().nextInt(100000,999999));
        user.setVerificationCode(verificationCode);
        user.setVerifiedAccount(false);

        User savedUser=userRepository.save(user);
        emailService.sendVerificationEmail(user.getEmail(),verificationCode);
        return savedUser;
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
