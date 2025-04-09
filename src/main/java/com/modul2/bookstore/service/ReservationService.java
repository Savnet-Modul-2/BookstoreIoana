package com.modul2.bookstore.service;

import com.modul2.bookstore.dto.ReservationsSearchFilterDTO;
import com.modul2.bookstore.entities.*;
import com.modul2.bookstore.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ExemplaryRepository exemplaryRepository;
    @Autowired
    private LibrarianRepository librarianRepository;

    public Reservation create(Reservation reservation, Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        Exemplary exemplary = exemplaryRepository
                .findFirstAvailableExemplary(bookId, reservation.getStartDate(), reservation.getEndDate())
                .orElseThrow(() -> new IllegalStateException("No available exemplaries in the period."));

        long daysBetween = Duration.between(
                reservation.getStartDate().atStartOfDay(),
                reservation.getEndDate().atStartOfDay()
        ).toDays();

        if (daysBetween > exemplary.getMaxRezervationDays()) {
            throw new UnsupportedOperationException("Reservation too long");
        }

        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setUser(user); //in copil setam parintele
        reservation.setExemplary(exemplary); //in copil setam parintele
        exemplary.setUpdateTime(LocalDateTime.now());

        exemplaryRepository.save(exemplary);

        return reservationRepository.save(reservation);
    }

    public Reservation updateStatus(Long librarianId, Long reservationId, ReservationStatus reservationStatus) {
        Reservation updateReservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        Librarian librarian = librarianRepository.findById(librarianId)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found"));

        if (!updateReservation.getStatus().isNextStatePossible(reservationStatus)) {
            throw new IllegalStateException("Cannot update status of reservation");
        }

        if (!librarian.getLibrary().equals(updateReservation.getExemplary().getBook().getLibrary())) {
            throw new IllegalStateException("Librarian does not have permission to update this reservation");
        }

        updateReservation.setStatus(reservationStatus);
        return reservationRepository.save(updateReservation);
    }
    public Page<Reservation> getReservationsByUser(Long userId, ReservationsSearchFilterDTO reservationsSearchFilterDTO) {
        PageRequest pageRequest = PageRequest.of(reservationsSearchFilterDTO.getPage(), reservationsSearchFilterDTO.getSize(), Sort.by(Sort.Direction.ASC, "status"));
        return reservationRepository.searchReservationsByFilterUser(userId,
                reservationsSearchFilterDTO.getStatuses(),
                reservationsSearchFilterDTO.getStartDate(),
                reservationsSearchFilterDTO.getEndDate(),
                pageRequest);
    }

    public Page<Reservation> getReservationsByPeriod(Long libraryId, ReservationsSearchFilterDTO reservationsSearchFilterDTO) {
        PageRequest pageRequest = PageRequest.of(reservationsSearchFilterDTO.getPage(), reservationsSearchFilterDTO.getSize(), Sort.by(Sort.Direction.ASC, "startDate"));
        return reservationRepository.searchReservationsByFilterLibrary(libraryId,
                reservationsSearchFilterDTO.getStatuses(),
                reservationsSearchFilterDTO.getStartDate(),
                reservationsSearchFilterDTO.getEndDate(),
                pageRequest
        );
    }
}