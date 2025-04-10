package com.modul2.bookstore.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {NotaValidator.class})
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface ValidNota {
    String message() default "Nota trebuie sa fie intre 1 si 5";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
