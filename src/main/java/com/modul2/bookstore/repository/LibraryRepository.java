package com.modul2.bookstore.repository;

import com.modul2.bookstore.entities.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    @Query(value= """
            SELECT l FROM library l
            JOIN l.users u
            WHERE(:userId = u.id)
            """)
    Page<Library> getLibrariesByUserId(Long userId, Pageable pageable);
}
