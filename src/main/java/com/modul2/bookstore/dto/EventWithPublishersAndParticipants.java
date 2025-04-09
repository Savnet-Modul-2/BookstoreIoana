package com.modul2.bookstore.dto;

import com.modul2.bookstore.dto.validation.AdvancedValidation;
import com.modul2.bookstore.dto.validation.BasicValidation;
import com.modul2.bookstore.dto.validation.DateNotInThePast;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class EventWithPublishersAndParticipants {
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

    private List<PublisherDTO> publishers;

    private List<ParticipantWithoutEventDTO> participants;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<PublisherDTO> getPublishers() {
        return publishers;
    }

    public List<ParticipantWithoutEventDTO> getParticipants() {
        return participants;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPublishers(List<PublisherDTO> publishers) {
        this.publishers = publishers;
    }

    public void setParticipants(List<ParticipantWithoutEventDTO> participants) {
        this.participants = participants;
    }
}
