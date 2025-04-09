package com.modul2.bookstore.controller;

import com.modul2.bookstore.dto.PublisherDTO;
import com.modul2.bookstore.dto.PublisherWithEventDTO;
import com.modul2.bookstore.entities.Publisher;
import com.modul2.bookstore.mapper.PublisherMapper;
import com.modul2.bookstore.repository.PublisherRepository;
import com.modul2.bookstore.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("publishers")
public class PublisherController {
    @Autowired
    PublisherService publisherService;

    @Autowired
    PublisherRepository publishierRepository;

    //create publisher without event
    @PostMapping("/publisher-without-event")
    public ResponseEntity<?> createPublisherWithoutEvent(@RequestBody PublisherDTO publisherDTO) {
        Publisher publisher = PublisherMapper.publisherDTO2Publisher(publisherDTO);
        Publisher createdPublisher = publisherService.createPublisherWithoutEvent(publisher);
        return ResponseEntity.ok(PublisherMapper.publisher2PublisherDTO(createdPublisher));
    }

    //add publisher to event
    @PostMapping("/add-publisher-to-event/{publisherId}/{eventId}")
    public ResponseEntity<?> addPublisherToEvent(@PathVariable Long publisherId,
                                                 @PathVariable Long eventId) {
        Publisher publisher = publisherService.addPublisherToEvent(publisherId, eventId);
        return ResponseEntity.ok(PublisherMapper.publisher2PublisherWithEventDTO(publisher));
    }

    //get all publishers from an event
    @GetMapping("/{eventId}")
    public ResponseEntity<?> getAllPublishersFromAnEvent(@PathVariable Long eventId) {
        List<Publisher> publishersFromAnEvent = publisherService.getPublishersFromAnEvent(eventId);
        List<PublisherDTO> publisherWithEventDTOS = publishersFromAnEvent.stream()
                .map(PublisherMapper::publisher2PublisherDTO)
                .toList();
        return ResponseEntity.ok(publisherWithEventDTOS);
    }

    @PutMapping("/remove/{publisherId}/from/{eventId}")
    public ResponseEntity<?>removePublisherFromEvent(@PathVariable Long publisherId,
                                                     @PathVariable Long eventId){
        publisherService.removePublisherFromEvent(publisherId,eventId);
        return ResponseEntity.noContent().build();
    }
}
