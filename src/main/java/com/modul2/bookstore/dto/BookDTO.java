package com.modul2.bookstore.dto;


import com.modul2.bookstore.dto.validation.BasicValidation;
import com.modul2.bookstore.entities.Category;
import com.modul2.bookstore.entities.Exemplary;
import com.modul2.bookstore.entities.Library;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class BookDTO {
    private Long id;
    @NotNull(groups = BasicValidation.class)
    private String title;
    @NotNull(groups = BasicValidation.class)
    private String author;
    private LocalDateTime appearanceDate;
    private int nrOfPages;
    private Category category;
    private String language;
    private LibraryDTO libraryDTO;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getAppearanceDate() {
        return appearanceDate;
    }

    public void setAppearanceDate(LocalDateTime appearanceDate) {
        this.appearanceDate = appearanceDate;
    }

    public int getNrOfPages() {
        return nrOfPages;
    }

    public void setNrOfPages(int nrOfPages) {
        this.nrOfPages = nrOfPages;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LibraryDTO getLibraryDTO() {
        return libraryDTO;
    }

    public void setLibraryDTO(LibraryDTO libraryDTO) {
        this.libraryDTO = libraryDTO;
    }
}
