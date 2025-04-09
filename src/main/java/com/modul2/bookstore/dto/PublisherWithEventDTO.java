package com.modul2.bookstore.dto;

import java.util.List;

public class PublisherWithEventDTO {
    private Long id;
    private String name;
    private String website;
    private String contactEmail;
    private List<EventWithoutPublisherDTO>events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public List<EventWithoutPublisherDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventWithoutPublisherDTO> events) {
        this.events = events;
    }
}
