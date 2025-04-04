package com.modul2.bookstore.mapper;

import com.modul2.bookstore.dto.BookDTO;
import com.modul2.bookstore.entities.Book;

public class BookMapper {
    public static Book bookDto2Book(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setAppearanceDate(bookDTO.getAppearanceDate());
        book.setNrOfPages(bookDTO.getNrOfPages());
        book.setCategory(bookDTO.getCategory());
        book.setLanguage(bookDTO.getLanguage());
        if (bookDTO.getLibraryDTO() != null) {
            book.setLibrary(LibraryMapper.libraryDto2Library(bookDTO.getLibraryDTO()));
        }
        return book;
    }

    public static BookDTO book2BookDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setAppearanceDate(book.getAppearanceDate());
        bookDTO.setNrOfPages(book.getNrOfPages());
        bookDTO.setCategory(book.getCategory());
        bookDTO.setLanguage(book.getLanguage());
        if (book.getLibrary() != null) {
            bookDTO.setLibraryDTO(LibraryMapper.library2LibraryDto(book.getLibrary()));
        }
        return bookDTO;
    }
}
