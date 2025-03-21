package com.modul2.bookstore.mapper;

import com.modul2.bookstore.dto.LibraryDTO;
import com.modul2.bookstore.entities.Library;

import java.util.ArrayList;

public class LibraryMapper {
    public static Library libraryDto2Library(LibraryDTO libraryDTO) {
        if (libraryDTO == null) {
            Library defaultLibrary = new Library();
            defaultLibrary.setName("Default Library");
            defaultLibrary.setAddress("Not set");
            defaultLibrary.setPhoneNumber("Not set");
            return defaultLibrary;
        }

        Library library = new Library();
        library.setName(libraryDTO.getName());
        library.setAddress(libraryDTO.getAddress());
        library.setPhoneNumber(libraryDTO.getPhoneNumber());
        library.setBooks(libraryDTO.getBooks() != null ?
                libraryDTO.getBooks().stream()
                        .map(BookMapper::bookDto2Book)
                        .peek(book -> book.setLibrary(library))
                        .toList()
                : new ArrayList<>());
        return library;
    }

    public static LibraryDTO library2LibraryDto(Library library) {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setId(library.getId());
        libraryDTO.setName(library.getName());
        libraryDTO.setAddress(library.getAddress());
        libraryDTO.setPhoneNumber(library.getPhoneNumber());
        libraryDTO.setBooks(library.getBooks().stream()
                .map(BookMapper::book2BookDto)
                .toList());
        return libraryDTO;
    }
}
