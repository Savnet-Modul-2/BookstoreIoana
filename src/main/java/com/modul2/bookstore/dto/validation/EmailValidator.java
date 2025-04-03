package com.modul2.bookstore.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

//validator personalizat
//Se ocupă cu validarea câmpurilor de tip String, care au fost adnotate cu @ValidEmail
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    //Se definește o expresie regulată simplă pentru adrese de email
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    //`EMAIL_PATTERN` este un obiect `Pattern`, folosit pentru validare rapidă și eficientă
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void initialize(ValidEmail validEmail) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    //determină dacă valoarea unui câmp este validă sau nu
    //apelata automat cand un camp are adnotarea @ValidEmail
}
