package com.modul2.bookstore.dto;

import com.modul2.bookstore.dto.validation.*;
import com.modul2.bookstore.entities.Library;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class UserDTO {
    private Long id;
    @NotNull(groups = BasicValidation.class)
    private String firstName;
    @NotNull(groups = BasicValidation.class)
    private String lastName;
    private Integer yearOfBirth;
    private String gender;
    @NotNull(groups = BasicValidation.class)
    @ValidEmail(groups = AdvancedValidation.class)
    private String email;
    @NotNull(groups = BasicValidation.class)
    @ValidPhoneNumber(groups = AdvancedValidation.class)
    private String phoneNumber;
    @NotNull(groups = BasicValidation.class)
    @ValidPassword(groups = AdvancedValidation.class)
    private String password;
    private String country;
    private Boolean verifiedAccount = false;
    private String verificationCode;
    private LocalDateTime verificationCodeExpiration;
    private List<LibraryDTO> libraryDTOS;

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

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public Boolean getVerifiedAccount() {
        return verifiedAccount;
    }

    public void setVerifiedAccount(Boolean verifiedAccount) {
        this.verifiedAccount = verifiedAccount;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public LocalDateTime getVerificationCodeExpiration() {
        return verificationCodeExpiration;
    }

    public void setVerificationCodeExpiration(LocalDateTime verificationCodeExpiration) {
        this.verificationCodeExpiration = verificationCodeExpiration;
    }

    public List<LibraryDTO> getLibraryDTOS() {
        return libraryDTOS;
    }

    public void setLibraryDTOS(List<LibraryDTO> libraryDTOS) {
        this.libraryDTOS = libraryDTOS;
    }
}
