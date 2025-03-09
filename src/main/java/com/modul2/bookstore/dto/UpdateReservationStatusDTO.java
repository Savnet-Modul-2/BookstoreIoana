package com.modul2.bookstore.dto;

import com.modul2.bookstore.entities.ReservationStatus;

public class UpdateReservationStatusDTO {
    private Long reservationId;
    private ReservationStatus reservationStatus;
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
