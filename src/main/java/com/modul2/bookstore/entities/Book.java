package com.modul2.bookstore.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity(name = "book")
@Table(name = "BOOK", schema = "public")
public class Book {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "APPEARANCE_DATE")
    private LocalDateTime appearanceDate;
    @Column(name = "NR_OF_PAGES")
    private Integer nrOfPages;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "LANGUAGE")
    private String language;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "library_id")
    private Library library;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "book")
    private List<Exemplary> exemplars;

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

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<Exemplary> getExemplars() {
        return exemplars;
    }

    public void setExemplars(List<Exemplary> exemplars) {
        this.exemplars = exemplars;
    }
    public void addExemplary(Exemplary exemplary) {
        exemplars.add(exemplary);
        exemplary.setBook(this);
    }

}