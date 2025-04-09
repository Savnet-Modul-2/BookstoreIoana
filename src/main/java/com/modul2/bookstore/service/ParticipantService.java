package com.modul2.bookstore.service;

import com.modul2.bookstore.entities.Event;
import com.modul2.bookstore.entities.Library;
import com.modul2.bookstore.entities.Participant;
import com.modul2.bookstore.repository.EventRepository;
import com.modul2.bookstore.repository.ParticipantRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private EventRepository eventRepository;

    public Participant createParticipantWithEvent(Participant participantToCreate) {
        if (participantToCreate.getId() != null) {
            throw new EntityExistsException("This participant already exists");
        }
        Event savedEvent = eventRepository.save(participantToCreate.getEvent());
        participantToCreate.setEvent(savedEvent);
        savedEvent.addParticipant(participantToCreate);
        return participantRepository.save(participantToCreate);
    }

    public Participant createParticipant(Participant participant) {
        if (participant.getId() != null) {
            throw new EntityExistsException("Participant already exists");
        }
        return participantRepository.save(participant);
    }

    public Participant getById(Long participantId) {
        return participantRepository.findById(participantId)
                .orElseThrow(() -> new EntityNotFoundException("Participant not found"));
    }

    public List<Participant> getParticipantsByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("This event doesn't exist"));
        List<Participant> participants = event.getParticipants();
        return participants;
    }
}
