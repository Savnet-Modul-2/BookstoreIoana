package com.modul2.bookstore.service;

import com.modul2.bookstore.entities.Event;
import com.modul2.bookstore.repository.EventRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event eventToCreate) {
        if (eventToCreate.getId() != null) {
            throw new EntityExistsException("This event already exists");
        }
        return eventRepository.save(eventToCreate);
    }

    public Event getById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
    }

    public Page<Event> getAllEventsPaginated(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    public Event updateEvent(Event existentEvent, Event newEvent) {
        existentEvent.setTitle(newEvent.getTitle());
        existentEvent.setDescription(newEvent.getDescription());
        existentEvent.setLocation(newEvent.getLocation());
        existentEvent.setDate(newEvent.getDate());

        return eventRepository.save(existentEvent);
    }

    public void deleteEvent(Long eventId){
        Event eventToDelete=eventRepository.findById(eventId)
                .orElseThrow(()->new EntityNotFoundException("This event doesn't exist"));
        eventRepository.delete(eventToDelete);
    }
}
