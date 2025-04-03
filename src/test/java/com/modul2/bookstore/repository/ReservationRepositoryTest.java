package com.modul2.bookstore.repository;

import com.modul2.bookstore.entities.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExemplaryRepository exemplaryRepository;
    private Reservation testReservation;
    private Exemplary testExemplary;
    private Book testBook;
    private Library testLibrary;
    private User testUser;

    @BeforeEach
    public void setUp() {
        testReservation = new Reservation();
        testExemplary = new Exemplary();
        testBook = new Book();
        testLibrary = new Library();
        testUser = new User();
        testExemplary.setBook(testBook);
        testBook.setExemplars(List.of(testExemplary));
        testLibrary.setBooks(List.of(testBook));
        testBook.setLibrary(testLibrary);
    }

    @AfterEach
    public void tearDown() {
        reservationRepository.deleteAll();
        libraryRepository.deleteAll();
        userRepository.deleteAll();
        bookRepository.deleteAll();
    }
    @Test
    public void givenStartDateAndStatus_findByStatusAndStartDate(){
        exemplaryRepository.save(testExemplary);
        bookRepository.save(testBook);
        libraryRepository.save(testLibrary);
        testReservation.setStartDate(LocalDate.now());
        testReservation.setStatus(ReservationStatus.PENDING);
        testReservation.setExemplary(testExemplary);
        reservationRepository.save(testReservation);

        List<Reservation> expected = reservationRepository.findByStatusAndStartDate(ReservationStatus.PENDING,LocalDate.now());

        Assertions.assertThat(expected).isNotEmpty();
    }
}
