package com.modul2.bookstore.mapper;

import com.modul2.bookstore.dto.EventDTO;
import com.modul2.bookstore.dto.ParticipantDTO;
import com.modul2.bookstore.dto.ParticipantWithoutEventDTO;
import com.modul2.bookstore.entities.Event;
import com.modul2.bookstore.entities.Participant;

public class ParticipantMapper {
    public static Participant participantDTO2Participant(ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setFirstName(participantDTO.getFirstName());
        participant.setLastName(participantDTO.getLastName());
        participant.setEmail(participantDTO.getEmail());
        participant.setPhoneNumber(participantDTO.getPhoneNumber());
        if (participantDTO.getEventDTO() != null) {
            participant.setEvent(EventMapper.eventDTO2Event(participantDTO.getEventDTO()));
        }
        return participant;
    }

    public static ParticipantDTO participant2ParticipantDTO(Participant participant) {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setId(participant.getId());
        participantDTO.setFirstName(participant.getFirstName());
        participantDTO.setLastName(participant.getLastName());
        participantDTO.setEmail(participant.getEmail());
        participantDTO.setPhoneNumber(participant.getPhoneNumber());
        if (participant.getEvent() != null) {
            participantDTO.setEventDTO(EventMapper.event2EventDTO(participant.getEvent()));
        }
        return participantDTO;
    }

    public static Participant participantWithoutEventDTO2Participant(ParticipantWithoutEventDTO participantDTO) {
        Participant p = new Participant();
        p.setFirstName(participantDTO.getFirstName());
        p.setLastName(participantDTO.getLastName());
        p.setEmail(participantDTO.getEmail());
        p.setPhoneNumber(participantDTO.getPhoneNumber());
        return p;
    }
    public static ParticipantWithoutEventDTO participant2ParticipantWithoutEvent(Participant participant) {
        ParticipantWithoutEventDTO pDTO = new ParticipantWithoutEventDTO();
        pDTO.setId(participant.getId());
        pDTO.setFirstName(participant.getFirstName());
        pDTO.setLastName(participant.getLastName());
        pDTO.setEmail(participant.getEmail());
        pDTO.setPhoneNumber(participant.getPhoneNumber());
        return pDTO;
    }
}
