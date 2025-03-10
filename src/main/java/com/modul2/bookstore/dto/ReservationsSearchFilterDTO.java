package com.modul2.bookstore.dto;

import com.modul2.bookstore.entities.ReservationStatus;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public class ReservationsSearchFilterDTO {
    private List<ReservationStatus>statuses;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer page;
    private Integer size;

    public List<ReservationStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<ReservationStatus> statuses) {
        this.statuses = statuses;
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
