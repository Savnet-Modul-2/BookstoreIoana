package com.modul2.bookstore.repository;

import com.modul2.bookstore.entities.Library;
import com.modul2.bookstore.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value= """
            SELECT u.libraries FROM user u
            WHERE(:userId = u.id)
            """)
    Page<Library> getLibrariesByUserId(Long userId, Pageable pageable);
    Optional<User> findByEmail(String email);
}
