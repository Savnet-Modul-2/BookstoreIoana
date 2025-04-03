package com.modul2.bookstore.repository;

import com.modul2.bookstore.entities.Librarian;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class LibrarianRepositoryTest {
    @Autowired
    private LibrarianRepository librarianRepository;
    private Librarian testLibrarian;

    @BeforeEach
    public void setUp() {
        testLibrarian = new Librarian();
        testLibrarian.setEmail("test@gmail.com");
    }

    @AfterEach
    public void tearDown() {
        librarianRepository.deleteAll();
    }
    @Test
    public void givenEmail_FindByEmail_ReturnLibrarian() {
        String testEmail = "test@gmail.com";

        librarianRepository.save(testLibrarian);
        Librarian foundLibrarian = librarianRepository.findByEmail(testEmail).orElse(null);

        Assertions.assertThat(foundLibrarian).isEqualTo(testLibrarian);
    }

    @Test
    public void givenNothing_FindByEmail_ReturnNull() {
        String testEmail = "test@gmail.com";

        Librarian foundLibrarian = librarianRepository.findByEmail(testEmail).orElse(null);

        Assertions.assertThat(foundLibrarian).isNull();
    }
}
