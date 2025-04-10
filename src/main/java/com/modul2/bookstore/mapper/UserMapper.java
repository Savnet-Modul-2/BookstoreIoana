package com.modul2.bookstore.mapper;

import com.modul2.bookstore.dto.LibraryDTO;
import com.modul2.bookstore.dto.UserDTO;
import com.modul2.bookstore.entities.Library;
import com.modul2.bookstore.entities.User;

import java.util.List;

public class UserMapper {
    public static User userDTO2User(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setYearOfBirth(userDTO.getYearOfBirth());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());
        user.setCountry(userDTO.getCountry());
        user.setVerifiedAccount(userDTO.getVerifiedAccount());
        user.setVerificationCode(userDTO.getVerificationCode());
        user.setVerificationCodeExpiration(userDTO.getVerificationCodeExpiration());
        return user;
    }

    public static UserDTO user2UserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setYearOfBirth(user.getYearOfBirth());
        userDTO.setGender(user.getGender());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setPassword(user.getPassword());
        userDTO.setCountry(user.getCountry());
        userDTO.setVerifiedAccount(user.getVerifiedAccount());
        userDTO.setVerificationCode(user.getVerificationCode());
        userDTO.setVerificationCodeExpiration(user.getVerificationCodeExpiration());
        if (user.getLibraries() != null) {
            List<LibraryDTO> librariesDTO = user.getLibraries().stream()
                    .map(LibraryMapper::library2LibraryDto)
                    .toList();
            userDTO.setLibraryDTOS(librariesDTO);
        }
        return userDTO;
    }
}
