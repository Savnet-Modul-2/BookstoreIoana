package com.modul2.bookstore.dto;

import com.modul2.bookstore.dto.validation.BasicValidation;
import com.modul2.bookstore.entities.Category;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class BookDTO {
    private Long id;
    @NotNull(groups = BasicValidation.class)
    private String title;
    @NotNull(groups = BasicValidation.class)
    private String author;
    private LocalDateTime appearanceDate;
    private Integer nrOfPages;
    private Category category;
    private String language;
    private LibraryDTO library;

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

    public Integer getNrOfPages() {
        return nrOfPages;
    }

    public void setNrOfPages(Integer nrOfPages) {
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

    public LibraryDTO getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDTO library) {
        this.library = library;
    }
}
