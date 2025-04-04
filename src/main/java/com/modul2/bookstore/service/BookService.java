package com.modul2.bookstore.service;

import com.modul2.bookstore.entities.Book;
import com.modul2.bookstore.entities.Library;
import com.modul2.bookstore.repository.BookRepository;
import com.modul2.bookstore.repository.LibraryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryRepository libraryRepository;

    public Book create(Book bookToCreate) {
        if (bookToCreate.getId() != null) {
            throw new RuntimeException("The book already exists");
        }
        return bookRepository.save(bookToCreate);
    }

    @Transactional
    public Book create(Book bookToCreate, Long libraryId) {
        if (bookToCreate.getId() != null) {
            throw new RuntimeException("The book already exists");
        }
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("This library doesn't exist"));

        library.addBook(bookToCreate);
        return bookRepository.save(bookToCreate);
    }

    @Transactional
    public void removeBookFromLibrary(Long bookId, Long libraryId) {
        Book bookToRemove = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("This book was not found"));

        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new EntityNotFoundException("This library was not found"));

        //library.removeBook(bookToRemove);--asta sterge cartea cu totul
        bookToRemove.setLibrary(null);
        bookRepository.save(bookToRemove);
    }

    public Page<Book> getAllBooksPaginated(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("This book was not found"));
        return book;
    }

    public Book updateBook(Long bookIdToUpdate, Book bookEntity) {
        Book updatedBook = bookRepository.findById(bookIdToUpdate)
                .orElseThrow(EntityNotFoundException::new);

        updatedBook.setTitle(bookEntity.getTitle());
        updatedBook.setAuthor(bookEntity.getAuthor());
        updatedBook.setAppearanceDate(bookEntity.getAppearanceDate());
        updatedBook.setNrOfPages(bookEntity.getNrOfPages());
        updatedBook.setCategory(bookEntity.getCategory());
        updatedBook.setLanguage(bookEntity.getLanguage());

        return bookRepository.save(updatedBook);
    }

    public Page<Book> findBooks(String author, String title, Pageable pageable) {
        return bookRepository.findBooks(author, title, pageable);
    }
}
