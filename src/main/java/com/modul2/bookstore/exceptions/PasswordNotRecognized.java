package com.modul2.bookstore.exceptions;

public class PasswordNotRecognized extends RuntimeException {
    public PasswordNotRecognized(String message) {
        super(message);
    }
}
