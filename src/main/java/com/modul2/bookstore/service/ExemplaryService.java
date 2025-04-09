package com.modul2.bookstore.service;

import com.modul2.bookstore.dto.ExemplaryDTO;
import com.modul2.bookstore.entities.Book;
import com.modul2.bookstore.entities.Exemplary;
import com.modul2.bookstore.mapper.ExemplaryMapper;
import com.modul2.bookstore.repository.BookRepository;
import com.modul2.bookstore.repository.ExemplaryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExemplaryService {
    @Autowired
    private ExemplaryRepository exemplaryRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Exemplary> create(String publisher, Integer maxReservationDays, Long bookId, Integer count) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));

        List<Exemplary> exemplars = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Exemplary exemplary = new Exemplary();
            exemplary.setPublisher(publisher);
            exemplary.setMaxRezervationDays(maxReservationDays);
            //relatie many to one
            book.addExemplary(exemplary); //adaugam fiecare exemplar in lista de exemplare din carti
            exemplars.add(exemplary);
        }

        return exemplaryRepository.saveAll(exemplars);
    }

    public Page<Exemplary> findByBookId(Long bookId, Integer page, Integer size) {
        return exemplaryRepository.findByBookId(bookId, PageRequest.of(page, size));
    }

    public void delete(Long exemplaryId) {
        exemplaryRepository.deleteById(exemplaryId);
    }
}
