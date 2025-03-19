package com.modul2.bookstore.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "reservation")
@Table(name = "RESERVATION", schema = "public")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ReservationStatus status;

    @Version
    @Column(name="version")
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exemplary_id", nullable = false)
    private Exemplary exemplary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exemplary getExemplary() {
        return exemplary;
    }

    public void setExemplary(Exemplary exemplary) {
        this.exemplary = exemplary;
    }
}
