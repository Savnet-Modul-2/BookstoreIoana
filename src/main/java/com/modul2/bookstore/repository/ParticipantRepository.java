package com.modul2.bookstore.repository;

import com.modul2.bookstore.entities.Event;
import com.modul2.bookstore.entities.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query(value = """
            SELECT p FROM participant p
            WHERE p.event IS NULL
            """)
    Page<Participant> findParticipantsWithoutEvent(Pageable pageable);
}
