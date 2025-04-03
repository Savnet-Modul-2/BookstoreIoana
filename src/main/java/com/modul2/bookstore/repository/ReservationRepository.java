package com.modul2.bookstore.repository;

import com.modul2.bookstore.dto.ReservationsSearchFilterDTO;
import com.modul2.bookstore.entities.Reservation;
import com.modul2.bookstore.entities.ReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStatusAndStartDate(ReservationStatus status, LocalDate startDate);

    List<Reservation> findByStatusAndEndDate(ReservationStatus status, LocalDate endDate);

    @Query("SELECT r FROM reservation r " +
            "WHERE r.user.id = :userId " +
            "AND (:statuses IS NULL OR r.status IN :statuses) " +
            "AND (cast(:startDate as date) IS NULL OR r.startDate >= :startDate) " +
            "AND (cast(:endDate as date) IS NULL OR r.endDate <= :endDate)")
    Page<Reservation> searchReservationsByFilterUser(Long userId,
                                                     List<ReservationStatus> statuses,
                                                     LocalDate startDate,
                                                     LocalDate endDate,
                                                     Pageable pageable);

    @Query("SELECT r FROM reservation r " +
            "JOIN r.exemplary e " +
            "JOIN e.book b " +
            "JOIN b.library l " +
            "WHERE l.id = :libraryId " +
            "AND (:statuses IS NULL OR r.status IN :statuses) " +
            "AND (cast(:startDate as date) IS NULL OR r.startDate >= :startDate) " +
            "AND (cast(:endDate as date) IS NULL OR r.endDate <= :endDate)")
    Page<Reservation> searchReservationsByFilterLibrary(Long libraryId,
                                                        List<ReservationStatus> statuses,
                                                        LocalDate startDate,
                                                        LocalDate endDate,
                                                        Pageable pageable);
}
