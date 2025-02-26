package com.modul2.bookstore.repository;

import com.modul2.bookstore.entities.Exemplary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExemplaryRepository extends JpaRepository<Exemplary, Long> {
    List<Exemplary> findByBookId(Long bookId);

    Page<Exemplary> findByBookId(Long bookId, Pageable pageable);
}
