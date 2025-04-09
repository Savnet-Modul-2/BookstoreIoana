package com.modul2.bookstore.service;

import com.modul2.bookstore.entities.Librarian;
import com.modul2.bookstore.entities.Library;
import com.modul2.bookstore.repository.LibrarianRepository;
import com.modul2.bookstore.repository.LibraryRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class LibrarianService {
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private EmailService emailService;

    public Librarian create(Librarian librarian) {
        if (librarian.getId() != null) {
            throw new EntityExistsException("Librarian with the email address %s already exists".formatted(librarian.getEmail()));
        }

        String sha256Hex = DigestUtils.sha256Hex(librarian.getPassword()).toUpperCase();
        librarian.setPassword(sha256Hex);

        librarian.setVerificationCode(String.valueOf(new Random().nextInt(10000, 99999)));
        librarian.setVerificationCodeTimeExpiration(LocalDateTime.now().plusMinutes(5));

        emailService.sendVerificationEmail(librarian.getEmail(), librarian.getVerificationCode());

        Library savedLibrary = libraryRepository.save(librarian.getLibrary());
        //relatie one to one
        librarian.setLibrary(savedLibrary); //setam parintele in copil
        savedLibrary.setLibrarian(librarian); //setam si copilul in parinte

        return librarianRepository.save(librarian);
    }

    public Librarian resendVerificationCode(String email) {
        Librarian librarian = librarianRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found"));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = librarian.getVerificationCodeTimeExpiration();

        if (now.isAfter(expirationTime.minusMinutes(1))) {
            String newVerificationCode = String.valueOf(new Random().nextInt(100000, 999999));
            librarian.setVerificationCode(newVerificationCode);
            librarian.setVerificationCodeTimeExpiration(now.plusMinutes(5));
        }

        emailService.sendVerificationEmail(librarian.getEmail(), librarian.getVerificationCode());
        return librarianRepository.save(librarian);
    }

    public Librarian verifyAccount(String email, String verificationCode) {
        Librarian librarian = librarianRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Librarian not found"));

        if (librarian.getVerificationCodeTimeExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("The verification code has expired.");
        }

        if (!librarian.getVerificationCode().equals(verificationCode)) {
            throw new RuntimeException("Invalid verification code.");
        }

        librarian.setVerificationCode(null);
        librarian.setVerificationCodeTimeExpiration(null);
        librarian.setVerifiedAccount(true);
        return librarianRepository.save(librarian);
    }

    public Librarian login(String email, String password) {
        Librarian librarian = librarianRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found"));
        String sha256Hex = DigestUtils.sha256Hex(password).toUpperCase();
        if (librarian.getPassword().equals(sha256Hex) && librarian.getVerifiedAccount()) {
            return librarian;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email or password");
    }
}
