package com.modul2.bookstore.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Constraint(validatedBy = { DateValidator.class})
@Target(ElementType.TYPE)//poate si aplicat doar pe o clasa/interfata
@Retention(RUNTIME)
public @interface ValidDate {
    String message() default "The start date must be less than the end date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
