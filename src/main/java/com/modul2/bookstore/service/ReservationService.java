package com.modul2.bookstore.service;

import com.modul2.bookstore.entities.*;
import com.modul2.bookstore.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setUser(user);
        reservation.setExemplary(exemplary);

        return reservationRepository.save(reservation);
    }

    public Reservation updateStatus(Long librarianId, Long reservationId, ReservationStatus reservationStatus) {
        Reservation updateReservation = reservationRepository.findById(reservationId).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        Librarian librarian = librarianRepository.findById(librarianId).orElseThrow(() -> new EntityNotFoundException("Librarian not found"));
        if (updateReservation.getStatus().isNextStatePossible(reservationStatus)) {
            throw new IllegalStateException("Reservation not found");
        }
        if (!librarian.getLibrary().equals(updateReservation.getExemplary().getBook().getLibrary())) {
            throw new IllegalStateException("Librarian does not have permission to update this reservation");
        }
        updateReservation.setStatus(reservationStatus);
        return reservationRepository.save(updateReservation);
    }
    public Page<Reservation> getReservationsByPeriod(LocalDate startDate, LocalDate endDate, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "startDate"));
        return reservationRepository.findByStartDateBetween(startDate, endDate, pageRequest);
    }
    public Page<Reservation> getReservationsByUser(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "status"));
        return reservationRepository.findByUserIdOrderByStatus(userId, pageRequest);
    }
}
