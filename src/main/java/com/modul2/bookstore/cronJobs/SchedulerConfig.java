package com.modul2.bookstore.cronJobs;

import com.modul2.bookstore.entities.*;
import com.modul2.bookstore.repository.ReservationRepository;
import com.modul2.bookstore.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 10 * * *")
    public void cancelExpiredPendingReservations() {
        LocalDate previousDay = LocalDate.now().minusDays(1);

        List<Reservation> expiredReservations = reservationRepository
                .findByStatusAndStartDate(ReservationStatus.PENDING, previousDay)
                .stream()
                .peek(reservation -> reservation.setStatus(ReservationStatus.CANCELED))
                .toList();

        reservationRepository.saveAll(expiredReservations);
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void flagOverdueReservations() {
        LocalDate previousDay = LocalDate.now().minusDays(1);

        List<Reservation> overdueReservations = reservationRepository
                .findByStatusAndEndDate(ReservationStatus.IN_PROGRESS, previousDay)
                .stream()
                .peek(reservation -> reservation.setStatus(ReservationStatus.DELAYED))
                .toList();

        reservationRepository.saveAll(overdueReservations);

        overdueReservations.forEach(this::notifyUsersOfOverdueBook);
    }

    private void notifyUsersOfOverdueBook(Reservation reservation) {
        User user = reservation.getUser();
        Exemplary exemplary = reservation.getExemplary();
        Book book = exemplary.getBook();
        Librarian librarian = book.getLibrary().getLibrarian();

        String bookTitle = book.getTitle();
        String userEmail = user.getEmail();
        String librarianEmail = librarian.getEmail();
        String userPhone = user.getPhoneNumber();

        emailService.sendEmailNotification(
                userEmail,
                "Book return",
                "You need to return the book '" + bookTitle + "'. Please bring it back as soon as possible."
        );

        emailService.sendEmailNotification(
                librarianEmail,
                "Unreturned Book!",
                "The user " + user.getFirstName() + " " + user.getLastName() +
                        " (Phone number: " + userPhone + ") has not returned '" + bookTitle + "'. Please contact them."
        );
    }

}
