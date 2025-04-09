package com.modul2.bookstore.dto;

import com.modul2.bookstore.dto.validation.AdvancedValidation;
import com.modul2.bookstore.dto.validation.BasicValidation;
import com.modul2.bookstore.dto.validation.ValidEmail;
import com.modul2.bookstore.dto.validation.ValidPhoneNumber;
import jakarta.validation.constraints.NotNull;

public class ParticipantWithoutEventDTO {
    private Long id;
    @NotNull(groups = BasicValidation.class)
    private String firstName;
    @NotNull(groups = BasicValidation.class)
    private String lastName;
    @NotNull(groups = BasicValidation.class)
    @ValidEmail(groups = AdvancedValidation.class)
    private String email;
    @NotNull(groups = BasicValidation.class)
    @ValidPhoneNumber(groups = AdvancedValidation.class)
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
