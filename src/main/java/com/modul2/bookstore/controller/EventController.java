package com.modul2.bookstore.controller;

import com.modul2.bookstore.dto.EventDTO;
import com.modul2.bookstore.dto.EventWithParticipantsDTO;
import com.modul2.bookstore.dto.EventWithPublishersAndParticipants;
import com.modul2.bookstore.dto.validation.BasicValidation;
import com.modul2.bookstore.dto.validation.ValidationOrder;
import com.modul2.bookstore.entities.Event;
import com.modul2.bookstore.mapper.EventMapper;
import com.modul2.bookstore.repository.EventRepository;
import com.modul2.bookstore.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {
    @Autowired
    EventService eventService;
    @Autowired
    EventRepository eventRepository;

    //create parent without children
    @PostMapping()
    public ResponseEntity<?> createEvent(@Validated(ValidationOrder.class)
                                         @RequestBody EventDTO eventDTO) {
        Event eventToCreate = EventMapper.eventDTO2Event(eventDTO);
        Event createdEvent = eventService.createEvent(eventToCreate);
        return ResponseEntity.ok(EventMapper.event2EventDTO(createdEvent));
    }

    //create parent with children
    @PostMapping("/with-participants")
    public ResponseEntity<?> createEventWithParticipants(@Validated(ValidationOrder.class)
                                                         @RequestBody EventWithParticipantsDTO eventDTO) {
        Event eventToCreate = EventMapper.eventWithParticipantsDTO2Event(eventDTO);
        Event createdEvent = eventService.createEvent(eventToCreate);
        return ResponseEntity.ok(EventMapper.event2EventWithParticipantsDTO(createdEvent));
    }

    @GetMapping("/paginated-search")
    public ResponseEntity<?> searchEventsByLocationAndTitle(@RequestParam(required = false) String location,
                                                            @RequestParam(required = false) String title,
                                                            @RequestParam(name = "pageSize", required = false) Integer size,
                                                            @RequestParam(name = "pageNumber", required = false) Integer page) {
        //valori implicite
        int pageSize = (size != null) ? size : 10;
        int pageNumber = (page != null) ? page : 0;

        Page<Event> foundEvents = eventRepository.findEventsByLocationAndTitle(location, title, PageRequest.of(pageNumber, pageSize));
        Page<EventDTO> foundEventsDTO = foundEvents.map(EventMapper::event2EventDTO);

        return ResponseEntity.ok(foundEventsDTO);
    }

    @GetMapping("/event-with-participants/{eventId}")
    public ResponseEntity<?> getEventWithParticipants(@PathVariable Long eventId) {
        Event eventWithParticipants = eventService.getById(eventId);
        return ResponseEntity.ok(EventMapper.event2EventWithParticipantsDTO(eventWithParticipants));
    }

    @GetMapping("/event-by-eventId/{eventId}")
    public ResponseEntity<?> getEventByEventId(@PathVariable Long eventId) {
        Event eventById = eventService.getById(eventId);
        return ResponseEntity.ok(EventMapper.event2EventDTO(eventById));
    }

    @GetMapping("/all-events-paginated")
    public ResponseEntity<?> getAllEventsPaginated(@RequestParam(name = "pageSize") int size,
                                                   @RequestParam(name = "pageNumber") int page) {
        Page<Event> events = eventService.getAllEventsPaginated(PageRequest.of(page, size));
        Page<EventDTO> eventDTOS = events.map(EventMapper::event2EventDTO);
        return ResponseEntity.ok(eventDTOS);
    }

    @PutMapping("/updateEvent/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable Long eventId, @RequestBody EventDTO eventDTO) {
        Event existentEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("This event doesn't exist"));
        Event newEvent = EventMapper.eventDTO2Event(eventDTO);
        Event updatedEvent = eventService.updateEvent(existentEvent, newEvent);
        return ResponseEntity.ok(EventMapper.event2EventDTO(updatedEvent));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingEvents(@RequestParam String location,
                                               @RequestParam(name = "pageSize") int size,
                                               @RequestParam(name = "pageNumber") int page) {

        Page<Event> foundEvents = eventRepository.findUpcomingEventsForLocation(location, PageRequest.of(page, size));
        Page<EventDTO> eventDTOS = foundEvents.map(EventMapper::event2EventDTO);
        return ResponseEntity.ok(eventDTOS);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }

    //top orase cu cele mai multe evenimente
    @GetMapping("/top-cities")
    public ResponseEntity<?> getTopCities() {
        List<Object[]> topCitiesWithMaxEvents = eventRepository.findTopCitiesWithMaxEvents();
        return ResponseEntity.ok(topCitiesWithMaxEvents);
    }

    //    Găsește toate evenimentele care:
//    se desfășoară într-un anumit oraș
//    au un anumit publisher asociat
//    au cel puțin un participant cu un anumit email
//    și urmează să aibă loc (date în viitor)
    @GetMapping("/query")
    public ResponseEntity<?> getAllEventsQuery(
            @RequestParam String location,
            @RequestParam Long publisherId,
            @RequestParam String email,
            @RequestParam LocalDate date,
            @RequestParam(name = "pageSize") int size,
            @RequestParam(name = "pageNumber") int page) {
        Page<Event> events = eventRepository.findEventsByLocationPublisherEmailDate(location, publisherId, email, date, PageRequest.of(page, size));
        Page<EventWithPublishersAndParticipants> eventDTOS = events.map(EventMapper::event2EventWithPublishersAndParticipantsDTO);
        return ResponseEntity.ok(eventDTOS);
    }


}
