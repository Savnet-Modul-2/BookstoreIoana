package com.modul2.bookstore.entities;

import jakarta.persistence.*;

@Entity(name = "exemplary")
@Table(name = "EXEMPLARY", schema = "public")
public class Exemplary {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "MAX_REZERVATION_DAYS")
    private Integer maxRezervationDays;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getMaxRezervationDays() {
        return maxRezervationDays;
    }

    public void setMaxRezervationDays(Integer maxRezervationDays) {
        this.maxRezervationDays = maxRezervationDays;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
