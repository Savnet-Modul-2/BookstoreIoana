package com.modul2.bookstore.dto.validation;

import com.modul2.bookstore.dto.ReservationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateValidatorNotInThePast implements ConstraintValidator<DateNotInThePast, LocalDate> {
    @Override
    public void initialize(DateNotInThePast validDatePast) {
    }
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return !date.isBefore(LocalDate.now());
    }
}
