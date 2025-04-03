package com.modul2.bookstore.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {EmailValidator.class})
//Leagă această adnotare de o clasă care implementează logica de validare – în cazul tău EmailValidator.
@Target(ElementType.FIELD)//adnotarea poate fi folosită pe câmpuri
@Retention(RUNTIME)//adnotare disponibilă la rulare
public @interface ValidEmail {
    //Atribute obligatorii:
    String message() default "Wrong email format";//Mesajul de eroare care va fi afișat când validarea eșuează

    Class<?>[] groups() default {};//Permite folosirea grupurilor de validare (ex: BasicValidation, AdvancedValidation).

    Class<? extends Payload>[] payload() default {};
}