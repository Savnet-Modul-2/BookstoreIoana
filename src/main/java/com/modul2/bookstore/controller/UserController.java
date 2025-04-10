package com.modul2.bookstore.controller;

import com.modul2.bookstore.dto.LibraryDTO;
import com.modul2.bookstore.dto.UserDTO;
import com.modul2.bookstore.dto.validation.ValidationOrder;
import com.modul2.bookstore.entities.Library;
import com.modul2.bookstore.entities.User;
import com.modul2.bookstore.exceptions.AccountNotVerifiedException;
import com.modul2.bookstore.mapper.LibraryMapper;
import com.modul2.bookstore.mapper.UserMapper;
import com.modul2.bookstore.repository.LibraryRepository;
import com.modul2.bookstore.repository.UserRepository;
import com.modul2.bookstore.service.LibraryService;
import com.modul2.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    LibraryRepository libraryRepository;

    //1.Primim dto ca parametru
//2.Convertim din dto in entity
//3.Entity entity = metoda din service(parametru entity)
//4.return ResponseEntity.ok(conversie in dto) la POST sau ResponseEntity.noContent().build() daca nu vrem sa afisam raspuns json
    @PostMapping()
    public ResponseEntity<?> create(@Validated(ValidationOrder.class)
                                    @RequestBody UserDTO userDTO) {
        User userToCreate = UserMapper.userDTO2User(userDTO);
        User createdUser = userService.create(userToCreate);
        return ResponseEntity.ok(UserMapper.user2UserDTO(createdUser));
    }

    @PostMapping("/resend-verification")
    public ResponseEntity<?> resendVerificationCode(@RequestParam("email") String email) {
        User user = userService.resendVerificationCode(email);
        return ResponseEntity.noContent().build();
    }

    //query param-optional in cale
    //path param-in cale
    @PutMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody UserDTO updatedUserDTO) {
        User userToUpdate = UserMapper.userDTO2User(updatedUserDTO);
        User updatedUser = userService.verify(userToUpdate.getEmail(), userToUpdate.getVerificationCode());
        return ResponseEntity.ok(UserMapper.user2UserDTO(updatedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO updatedUserDTO) throws AccountNotVerifiedException {
        User userToLogin = UserMapper.userDTO2User(updatedUserDTO);
        User loggedinUser = userService.login(userToLogin.getEmail(), userToLogin.getPassword());
        return ResponseEntity.ok(UserMapper.user2UserDTO(loggedinUser));
    }

    @PostMapping("/add/{libraryId}/to/{userId}/favorites")
    public ResponseEntity<?> addLibraryToUsersFavorites(@PathVariable Long libraryId,
                                                        @PathVariable Long userId) {
        User user = userService.addLibraryToUsersFavorites(userId, libraryId);
        return ResponseEntity.ok(UserMapper.user2UserDTO(user));
    }

    @PutMapping("/remove/{libraryId}/from/{userId}/favorites")
    public ResponseEntity<?> removeLibraryFromUsersFavorites(@PathVariable Long libraryId,
                                                             @PathVariable Long userId) {
        userService.removeLibraryFromUsersFavorites(userId, libraryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/favorites/{userId}")
    public ResponseEntity<?> paginatedFavoriteLibraries(@PathVariable Long userId,
                                                        @RequestParam(required = false) Integer page,
                                                        @RequestParam(required = false) Integer size) {
        Page<Library> libraries = libraryRepository.getLibrariesByUserId(userId, PageRequest.of(page,size));
        Page<LibraryDTO> libraryDTOS = libraries.map(LibraryMapper::library2LibraryDto);
        return ResponseEntity.ok(libraryDTOS);
    }

}
