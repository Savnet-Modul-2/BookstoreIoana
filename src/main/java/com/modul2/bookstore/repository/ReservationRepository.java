package com.modul2.bookstore.repository;

import com.modul2.bookstore.entities.Reservation;
import com.modul2.bookstore.entities.ReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStatusAndStartDate(ReservationStatus status, LocalDate startDate);

    List<Reservation> findByStatusAndEndDate(ReservationStatus status, LocalDate endDate);
    Page<Reservation> findByStartDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Reservation> findByUserIdOrderByStatus(Long userId, Pageable pageable);
}
