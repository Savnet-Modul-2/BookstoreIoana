package com.modul2.bookstore.service;

import com.modul2.bookstore.entities.User;
import com.modul2.bookstore.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        String sha256Hex = DigestUtils.sha256Hex(user.getPassword()).toUpperCase();
        user.setPassword(sha256Hex);
        return userRepository.save(user);
    }
}
