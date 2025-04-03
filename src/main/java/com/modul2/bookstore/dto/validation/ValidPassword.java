package com.modul2.bookstore.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {PasswordValidator.class})
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface ValidPassword {
    String message() default "Not enough characters. It has to be at least 6 characters long";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
