package com.modul2.bookstore.mapper;

import com.modul2.bookstore.dto.ReservationDTO;
import com.modul2.bookstore.entities.Reservation;

public class ReservationMapper {
    public static Reservation reservationDTO2Reservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStatus(reservation.getStatus());
        return reservation;
    }

    public static ReservationDTO reservation2ReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setStatus(reservation.getStatus());
        reservationDTO.setExemplary(ExemplaryMapper.exemplary2ExemplaryDto(reservation.getExemplary()));
        //reservationDTO.setUser(UserMapper.user2UserDTO(reservation.getUser()));
        return reservationDTO;
    }
}
