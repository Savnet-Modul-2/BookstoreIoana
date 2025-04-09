package com.modul2.bookstore.mapper;


import com.modul2.bookstore.dto.*;
import com.modul2.bookstore.entities.Event;
import com.modul2.bookstore.entities.Participant;
import com.modul2.bookstore.entities.Publisher;

import java.util.List;

public class EventMapper {
    public static Event eventDTO2Event(EventDTO eventDTO) {
        Event event = new Event();
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setDate(eventDTO.getDate());

        if (eventDTO.getPublishers() != null) {
            List<Publisher> publishers = eventDTO.getPublishers().stream()
                    .map(PublisherMapper::publisherDTO2Publisher)
                    .toList();
            event.setPublishers(publishers);
        }
        return event;
    }

    public static EventDTO event2EventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setDate(event.getDate());

        if (event.getPublishers() != null) {
            List<PublisherDTO> publisherDTOS = event.getPublishers().stream()
                    .map(PublisherMapper::publisher2PublisherDTO)
                    .toList();
            eventDTO.setPublishers(publisherDTOS);
        }
        return eventDTO;
    }

    public static Event eventWithParticipantsDTO2Event(EventWithParticipantsDTO eventDTO) {
        Event event = new Event();
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setDate(eventDTO.getDate());

        if (eventDTO.getParticipants() != null) {
            List<Participant> participants = eventDTO.getParticipants().stream()
                    .map(ParticipantMapper::participantWithoutEventDTO2Participant)
                    .peek(p -> p.setEvent(event))
                    .toList();
            event.setParticipants(participants);
        }
        return event;
    }

    public static EventWithParticipantsDTO event2EventWithParticipantsDTO(Event event) {
        EventWithParticipantsDTO eventDTO = new EventWithParticipantsDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setDate(event.getDate());

        if (event.getParticipants() != null) {
            List<ParticipantWithoutEventDTO> participantsDTOs = event.getParticipants().stream()
                    .map(ParticipantMapper::participant2ParticipantWithoutEvent)
                    .toList();
            eventDTO.setParticipants(participantsDTOs);
        }
        return eventDTO;
    }

    public static EventWithoutPublisherDTO event2EventWithoutPublisherDTO(Event event) {
        EventWithoutPublisherDTO eventDTO = new EventWithoutPublisherDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setDate(event.getDate());
        return eventDTO;
    }

    public static EventWithPublishersAndParticipants event2EventWithPublishersAndParticipantsDTO(Event event) {
        EventWithPublishersAndParticipants eventDTO = new EventWithPublishersAndParticipants();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setDate(event.getDate());
        if (event.getParticipants() != null) {
            List<ParticipantWithoutEventDTO> participantsDTOs = event.getParticipants().stream()
                    .map(ParticipantMapper::participant2ParticipantWithoutEvent)
                    .toList();
            eventDTO.setParticipants(participantsDTOs);
        }
        if (event.getPublishers() != null) {
            List<PublisherDTO> publisherDTOS = event.getPublishers().stream()
                    .map(PublisherMapper::publisher2PublisherDTO)
                    .toList();
            eventDTO.setPublishers(publisherDTOS);
        }
        return eventDTO;
    }
}
