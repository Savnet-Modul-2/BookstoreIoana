package com.modul2.bookstore.dto.validation;

import com.modul2.bookstore.dto.ReservationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDate, ReservationDTO> {
    @Override
    public void initialize(ValidDate validDate) {
    }

    @Override
    public boolean isValid(ReservationDTO reservationDto, ConstraintValidatorContext context) {
        return !reservationDto.getStartDate().isAfter(reservationDto.getEndDate());
    }
}
