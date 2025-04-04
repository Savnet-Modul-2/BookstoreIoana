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
    BookService bookService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody BookDTO bookDTO) {
        Book bookToCreate = BookMapper.bookDto2Book(bookDTO);
        Book createdBook = bookService.create(bookToCreate);
        return ResponseEntity.ok(BookMapper.book2BookDto(createdBook));
    }

    @PostMapping("/library/{libraryId}")
    public ResponseEntity<?> createBookToLibrary(@RequestBody BookDTO bookDTO,
                                                 @PathVariable Long libraryId) {
        Book bookToCreate = BookMapper.bookDto2Book(bookDTO);
        Book createdBook = bookService.create(bookToCreate, libraryId);
        return ResponseEntity.ok(BookMapper.book2BookDto(createdBook));
    }

    @PutMapping("/remove/{bookId}/from/{libraryId}")
    public ResponseEntity<?> removeBookFromLibrary(@PathVariable Long bookId,
                                                   @PathVariable Long libraryId) {
        bookService.removeBookFromLibrary(bookId, libraryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId);
        return ResponseEntity.ok(BookMapper.book2BookDto(book));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId,
                                        @RequestBody BookDTO updateModelBookDTO) {
        Book updateModelBook = BookMapper.bookDto2Book(updateModelBookDTO);
        Book bookToUpdate = bookService.updateBook(bookId, updateModelBook);
        return ResponseEntity.ok(BookMapper.book2BookDto(bookToUpdate));
    }

    @GetMapping("/paginated")
    public ResponseEntity<?> findAllPaginated(
            @RequestParam(name = "pageSize") int size,
            @RequestParam(name = "pageNumber") int page) {

        Page<Book> foundBooks = bookService.getAllBooksPaginated(PageRequest.of(page, size));
        Page<BookDTO> bookDtos = foundBooks.map(BookMapper::book2BookDto);

        return ResponseEntity.ok(bookDtos);
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
