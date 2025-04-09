package com.modul2.bookstore.service;

import com.modul2.bookstore.entities.Event;
import com.modul2.bookstore.entities.Publisher;
import com.modul2.bookstore.repository.EventRepository;
import com.modul2.bookstore.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    EventRepository eventRepository;

    public Publisher createPublisherWithoutEvent(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Transactional
    public Publisher addPublisherToEvent(Long publisherId, Long eventId) {
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new EntityNotFoundException("The publisher doesn't exist"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("The event doesn't exist"));
        publisher.addEvent(event);
        event.addPublisher(publisher);
        eventRepository.save(event);
        return publisherRepository.save(publisher);
    }

    public List<Publisher> getPublishersFromAnEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("The event doesn't exist"));
        return event.getPublishers();
    }

    @Transactional
    public void removePublisherFromEvent(Long publisherId, Long eventId) {
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new EntityNotFoundException("This publisher doesn't exist"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("This event doesn't exist"));

        event.removePublisher(publisher);

        eventRepository.save(event);
        publisherRepository.save(publisher);
    }
}
