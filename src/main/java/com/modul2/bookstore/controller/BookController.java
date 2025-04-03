package com.modul2.bookstore.controller;

import com.modul2.bookstore.dto.BookDTO;
import com.modul2.bookstore.entities.Book;
import com.modul2.bookstore.mapper.BookMapper;
import com.modul2.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody BookDTO bookDto) {
        Book bookToCreate = BookMapper.bookDto2Book(bookDto);
        Book createdBook = bookService.create(bookToCreate);
        return ResponseEntity.ok(BookMapper.book2BookDto(createdBook));
    }

    @PostMapping("/library/{libraryId}")
    public ResponseEntity<?> create(@PathVariable Long libraryId, @RequestBody BookDTO bookDTO) {
        Book bookToCreate = BookMapper.bookDto2Book(bookDTO);
        Book createdBook = bookService.create(libraryId, bookToCreate);
        return ResponseEntity.ok(BookMapper.book2BookDto(createdBook));
    }

    @GetMapping("/paginated")
    public ResponseEntity<?> findAllPaginated(
            @RequestParam(name = "pageSize") int size,
            @RequestParam(name = "pageNumber") int page) {

        Page<Book> foundBooks = bookService.findAll(PageRequest.of(page, size));
        Page<BookDTO> bookDtos = foundBooks.map(BookMapper::book2BookDto);

        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getById(@PathVariable Long bookId) {
        Book book = bookService.getById(bookId);
        return ResponseEntity.ok(BookMapper.book2BookDto(book));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "bookId") Long bookIdToDelete) {
        bookService.deleteById(bookIdToDelete);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateById(@PathVariable(name = "bookId") Long bookIdToUpdate, @RequestBody BookDTO bookBody) {
        Book bookEntity = BookMapper.bookDto2Book(bookBody);
        Book updatedBook = bookService.updateById(bookIdToUpdate, bookEntity);
        return ResponseEntity.ok(BookMapper.book2BookDto(updatedBook));
    }

    @DeleteMapping("/{bookId}/library")
    public ResponseEntity<?> removeFromLibrary(@PathVariable Long bookId) {
        bookService.removeFromLibrary(bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated-search")
    public ResponseEntity<?> findBooksPaginated(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title,
            @RequestParam(name = "pageSize", required = false) Integer size,
            @RequestParam(name = "pageNumber", required = false) Integer page) {

        int pageSize = (size != null) ? size : 10; // Valoare implicită
        int pageNumber = (page != null) ? page : 0; // Valoare implicită

        Page<Book> foundBooks = bookService.findBooks(author, title, PageRequest.of(page, size));
        Page<BookDTO> bookDtos = foundBooks.map(BookMapper::book2BookDto);

        return ResponseEntity.ok(bookDtos);
    }
}
