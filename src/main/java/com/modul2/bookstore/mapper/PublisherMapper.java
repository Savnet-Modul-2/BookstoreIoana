package com.modul2.bookstore.mapper;

import com.modul2.bookstore.dto.*;
import com.modul2.bookstore.entities.Event;
import com.modul2.bookstore.entities.Publisher;

import java.util.List;

public class PublisherMapper {
    public static Publisher publisherDTO2Publisher(PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setName(publisherDTO.getName());
        publisher.setWebsite(publisherDTO.getWebsite());
        publisher.setContactEmail(publisherDTO.getContactEmail());
        return publisher;
    }

    public static PublisherDTO publisher2PublisherDTO(Publisher publisher) {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        publisherDTO.setWebsite(publisher.getWebsite());
        publisherDTO.setContactEmail(publisher.getContactEmail());
        return publisherDTO;
    }

    public static PublisherWithEventDTO publisher2PublisherWithEventDTO(Publisher publisher) {
        PublisherWithEventDTO publisherDTO = new PublisherWithEventDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        publisherDTO.setWebsite(publisher.getWebsite());
        publisherDTO.setContactEmail(publisher.getContactEmail());
        if (publisher.getEvents() != null) {
            List<EventWithoutPublisherDTO> eventsWithoutPublishers = publisher.getEvents().stream()
                    .map(EventMapper::event2EventWithoutPublisherDTO)
                    .toList();
            publisherDTO.setEvents(eventsWithoutPublishers);
        }
        return publisherDTO;
    }
}
