package com.modul2.bookstore.service;

import com.modul2.bookstore.entities.User;
import com.modul2.bookstore.exceptions.AccountNotVerifiedException;
import com.modul2.bookstore.exceptions.PasswordNotRecognized;
import com.modul2.bookstore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
//Metodele din service primesc entities ca parametrii

    public User create(User user) {
        String sha256Hex = DigestUtils.sha256Hex(user.getPassword().toUpperCase());
        user.setPassword(sha256Hex);

        String verificationCode = String.valueOf(new Random().nextInt(100000, 999999));
        user.setVerificationCode(verificationCode);
        user.setVerificationCodeExpiration(LocalDateTime.now().plusMinutes(10));
        user.setVerifiedAccount(false);

        User savedUser = userRepository.save(user);
        emailService.sendVerificationEmail(user.getEmail(), verificationCode);
        return savedUser;
    }

    public User resendVerificationCode(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = user.getVerificationCodeExpiration();

        if (now.isAfter(expirationTime.minusMinutes(1))) {
            String newVerificationCode = String.valueOf(new Random().nextInt(100000, 999999));
            user.setVerificationCode(newVerificationCode);
            user.setVerificationCodeExpiration(now.plusMinutes(5));
        }
        emailService.sendVerificationEmail(user.getEmail(), user.getVerificationCode());
        return userRepository.save(user);
    }

    public User verify(String email, String verificationCode) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (user.getVerificationCodeExpiration() != null && LocalDateTime.now().isAfter(user.getVerificationCodeExpiration())) {
            throw new RuntimeException("Verification code has expired. Please request a new one.");
        }

        if (!user.getVerificationCode().equals(verificationCode)) {
            throw new RuntimeException("Invalid verification code.");
        }
        user.setVerifiedAccount(true);
        user.setVerificationCode(null);
        user.setVerificationCodeExpiration(null);

        return userRepository.save(user);
    }

    public User login(String email, String password) throws AccountNotVerifiedException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email not found"));
        String sha256Hex = DigestUtils.sha256Hex(password);

        if (user.getPassword().equals(sha256Hex) && user.getVerifiedAccount()) {
            return user;
        }

        if (!user.getVerifiedAccount()) {
            throw new AccountNotVerifiedException("Account not verified");
        }

        if (!user.getPassword().equals(sha256Hex)) {
            throw new PasswordNotRecognized("Incorrect password");
        }

        return userRepository.save(user);
    }

}
