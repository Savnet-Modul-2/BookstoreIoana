package com.modul2.bookstore.mapper;

import com.modul2.bookstore.dto.LibrarianDTO;
import com.modul2.bookstore.entities.Librarian;

public class LibrarianMapper {
    public static Librarian librarianDto2Librarian(LibrarianDTO librarianDTO) {
        Librarian librarian = new Librarian();
        librarian.setFirstName(librarianDTO.getFirstName());
        librarian.setLastName(librarianDTO.getLastName());
        librarian.setEmail(librarianDTO.getEmail());
        librarian.setPassword(librarianDTO.getPassword());

        if (librarianDTO.getLibraryDTO() != null) {
            librarian.setLibrary(LibraryMapper.libraryDto2Library(librarianDTO.getLibraryDTO()));
        }

        librarian.setVerifiedAccount(librarianDTO.getVerifiedAccount());
        librarian.setVerificationCode(librarianDTO.getVerificationCode());
        librarian.setVerificationCodeTimeExpiration(librarianDTO.getVerificationCodeTimeExpiration());

        return librarian;
    }


    public static LibrarianDTO librarian2LibrarianDto(Librarian librarian) {
        LibrarianDTO librarianDTO = new LibrarianDTO();
        librarianDTO.setId(librarian.getId());
        librarianDTO.setFirstName(librarian.getFirstName());
        librarianDTO.setLastName(librarian.getLastName());
        librarianDTO.setEmail(librarian.getEmail());
        librarianDTO.setPassword(librarian.getPassword());
        librarianDTO.setLibraryDTO(LibraryMapper.library2LibraryDto(librarian.getLibrary()));

        if (librarian.getLibrary() != null) {
            librarianDTO.setLibraryDTO(LibraryMapper.library2LibraryDto(librarian.getLibrary()));
        }

        librarianDTO.setVerifiedAccount(librarian.getVerifiedAccount());
        librarianDTO.setVerificationCode(librarian.getVerificationCode());
        librarianDTO.setVerificationCodeTimeExpiration(librarian.getVerificationCodeTimeExpiration());
        return librarianDTO;
    }
}