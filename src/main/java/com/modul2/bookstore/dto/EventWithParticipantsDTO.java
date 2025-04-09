package com.modul2.bookstore.dto;

import com.modul2.bookstore.dto.validation.AdvancedValidation;
import com.modul2.bookstore.dto.validation.BasicValidation;
import com.modul2.bookstore.dto.validation.DateNotInThePast;
import com.modul2.bookstore.entities.Participant;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class EventWithParticipantsDTO {
    private Long id;
    @NotNull(groups = BasicValidation.class)
    private String title;
    @NotNull(groups = BasicValidation.class)
    private String description;
    @NotNull(groups = BasicValidation.class)
    private String location;
    @NotNull(groups = BasicValidation.class)
    @DateNotInThePast(groups = AdvancedValidation.class)
    private LocalDate date;
    private List<ParticipantWithoutEventDTO> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ParticipantWithoutEventDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantWithoutEventDTO> participants) {
        this.participants = participants;
    }
}
