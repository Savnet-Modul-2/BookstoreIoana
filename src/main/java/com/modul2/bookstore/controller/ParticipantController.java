package com.modul2.bookstore.controller;

import com.modul2.bookstore.dto.ParticipantDTO;
import com.modul2.bookstore.dto.validation.ValidationOrder;
import com.modul2.bookstore.entities.Event;
import com.modul2.bookstore.entities.Participant;
import com.modul2.bookstore.mapper.ParticipantMapper;
import com.modul2.bookstore.repository.EventRepository;
import com.modul2.bookstore.repository.ParticipantRepository;
import com.modul2.bookstore.service.EventService;
import com.modul2.bookstore.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("participants")
public class ParticipantController {
    @Autowired
    ParticipantService participantService;
    @Autowired
    EventService eventService;
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    EventRepository eventRepository;

    //create children
    @PostMapping("/create-participant-with-event")
    public ResponseEntity<?> create(@Validated(ValidationOrder.class)
                                    @RequestBody ParticipantDTO participantDTO) {
        Participant participantToCreate = ParticipantMapper.participantDTO2Participant(participantDTO);
        Participant createdParticipant = participantService.createParticipantWithEvent(participantToCreate);
        return ResponseEntity.ok(ParticipantMapper.participant2ParticipantDTO(createdParticipant));
    }

    //add-children-to-parent
    @PostMapping("/add-participant-to-event/{eventId}/{participantId}")
    public ResponseEntity<?> addParticipantToEvent(@PathVariable Long eventId,
                                                   @PathVariable Long participantId) {
        Event event = eventService.getById(eventId);
        Participant participant = participantService.getById(participantId);
        participant.setEvent(event);
        event.getParticipants().add(participant);
        eventRepository.save(event);
        return ResponseEntity.ok(ParticipantMapper.participant2ParticipantDTO(participant));
    }

    //create-without-event
    @PostMapping("/create-participant-without-event")
    public ResponseEntity<ParticipantDTO> createWithoutEvent(@Validated(ValidationOrder.class)
                                                             @RequestBody ParticipantDTO participantDTO) {
        Participant participant = ParticipantMapper.participantDTO2Participant(participantDTO);
        participant.setEvent(null); // asigurăm că e null explicit
        Participant created = participantService.createParticipant(participant);
        return ResponseEntity.ok(ParticipantMapper.participant2ParticipantDTO(created));
    }

    @GetMapping("/participants-by-eventId/{eventId}")
    public ResponseEntity<?> getParticipantsByEventId(@PathVariable Long eventId) {
        List<Participant> participantsByEventId = participantService.getParticipantsByEventId(eventId);
        List<ParticipantDTO> participantDTOS = participantsByEventId.stream().map(ParticipantMapper::participant2ParticipantDTO).toList();
        return ResponseEntity.ok(participantDTOS);
    }

    @GetMapping("/participants")
    public ResponseEntity<?> getParticipants() {
        List<Participant> allParticipants = participantRepository.findAll();
        List<ParticipantDTO> allParticipantsDTO = allParticipants.stream().map(ParticipantMapper::participant2ParticipantDTO).toList();
        return ResponseEntity.ok(allParticipantsDTO);
    }

    @GetMapping("/participant-with-no-event")
    public ResponseEntity<?> getParticipantsWithoutEvent(@RequestParam(name = "pageSize", required = false) Integer size,
                                                         @RequestParam(name = "pageNumber", required = false) Integer page) {
        int pageSize = (size != null) ? size : 10;
        int pageNumber = (page != null) ? page : 0;
        Page<Participant> participantsWithoutEvent = participantRepository.findParticipantsWithoutEvent(PageRequest.of(pageNumber, pageSize));
        Page<ParticipantDTO> participantDTOS = participantsWithoutEvent.map(ParticipantMapper::participant2ParticipantDTO);

        return ResponseEntity.ok(participantDTOS);
    }
}
