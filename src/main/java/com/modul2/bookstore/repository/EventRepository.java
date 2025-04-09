package com.modul2.bookstore.repository;

import com.modul2.bookstore.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = """
            SELECT e FROM event e
            WHERE (:location IS NULL OR LOWER(e.location) LIKE LOWER(CONCAT('%', :location, '%')))
            AND (:title IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :title, '%')))
            """)
    Page<Event> findEventsByLocationAndTitle(String location, String title, Pageable pageable);

    @Query(value = """
            SELECT e FROM event e
            WHERE(LOWER(:location) LIKE LOWER(e.location))
            AND(e.date > CURRENT_DATE)
            """)
    Page<Event> findUpcomingEventsForLocation(String location, Pageable pageable);

    @Query(value = """
            SELECT e.location, COUNT(e)
            FROM event e
            GROUP BY e.location
            ORDER BY COUNT(e) DESC
            """)
    List<Object[]> findTopCitiesWithMaxEvents();

    @Query(value = """
            SELECT e from event e
            JOIN e.publishers pu
            JOIN e.participants pa
            WHERE(:location IS NULL OR LOWER(e.location) LIKE LOWER(CONCAT('%', :location, '%')))
            AND(:publisherId IS NULL OR :publisherId = pu.id)
            AND(:email IS NULL OR LOWER(pa.email) LIKE LOWER(CONCAT('%', :email, '%')))
            AND(cast(:date as date) IS NULL OR e.date>:date)
            """)
    Page<Event> findEventsByLocationPublisherEmailDate(String location, Long publisherId, String email, LocalDate date, Pageable pageable);
}
