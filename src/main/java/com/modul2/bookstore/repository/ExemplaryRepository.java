package com.modul2.bookstore.repository;

import com.modul2.bookstore.entities.Exemplary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExemplaryRepository extends JpaRepository<Exemplary, Long> {
    List<Exemplary> findByBookId(Long bookId);

    Page<Exemplary> findByBookId(Long bookId, Pageable pageable);

    @Query(value = """
            SELECT * FROM exemplary e
            WHERE e.id NOT IN (
                SELECT r.exemplary_id FROM reservation r
                WHERE ((r.start_date <= :endDate AND r.end_date >= :startDate)
                AND r.status IN ('IN_PROGRESS', 'PENDING'))
                OR r.status = 'DELAYED'
            )
            AND e.book_id = :bookId
            LIMIT 1
            """, nativeQuery = true)
    Optional<Exemplary> findFirstAvailableExemplary(Long bookId, LocalDate startDate, LocalDate endDate);
    /*
    — Selectează toate exemplarele de cărți.
    — Exclude exemplarele care sunt deja rezervate într-un interval care se suprapune cu cel primit ca parametri.
    — Exemplare indisponibile: Au o stare de tip IN_PROGRESS, PENDING și se suprapun cu perioada cerută. Sau sunt deja DELAYED (adică întârziate)
    — Se caută doar exemplare pentru cartea cu bookId dat.
    — Returnează doar primul exemplar liber găsit (dacă există).

    start rezervare    <=    endDate ul din param
    end rezervare      >=    startDate ul din param

     */
}
