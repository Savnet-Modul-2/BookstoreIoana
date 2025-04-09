package com.modul2.bookstore.dto;

import com.modul2.bookstore.entities.Event;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class PublisherDTO {
    private Long id;
    private String name;
    private String website;
    private String contactEmail;

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
}
