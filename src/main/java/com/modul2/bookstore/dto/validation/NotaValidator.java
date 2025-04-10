package com.modul2.bookstore.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotaValidator implements ConstraintValidator<ValidNota, Integer> {
    @Override
    public void initialize(ValidNota validNota) {
    }

    @Override
    public boolean isValid(Integer nota, ConstraintValidatorContext context) {
        return nota >= 1 && nota <= 5;
    }
}
