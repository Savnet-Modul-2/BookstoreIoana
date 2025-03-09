package com.modul2.bookstore.controller;

import com.modul2.bookstore.dto.ReservationDTO;
import com.modul2.bookstore.dto.UpdateReservationStatusDTO;
import com.modul2.bookstore.dto.validation.ValidationOrder;
import com.modul2.bookstore.entities.Reservation;
import com.modul2.bookstore.entities.ReservationStatus;
import com.modul2.bookstore.mapper.ReservationMapper;
import com.modul2.bookstore.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<?> create(@PathVariable Long userId,
                                    @PathVariable Long bookId,
                                    @Validated(ValidationOrder.class)
                                    @RequestBody ReservationDTO reservationDTO) {
        Reservation reservationToCreate = ReservationMapper.reservationDTO2Reservation(reservationDTO);
        Reservation createdReservation = reservationService.create(reservationToCreate, userId, bookId);
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(createdReservation));
    }

    @PutMapping("/{librarianId}")
    public ResponseEntity<?> updateStatus(@PathVariable Long librarianId, @RequestBody UpdateReservationStatusDTO reservationStatusDTO) {
        Reservation updatedReservation = reservationService.updateStatus(librarianId, reservationStatusDTO.getReservationId(), reservationStatusDTO.getReservationStatus());
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(updatedReservation));
    }
    @GetMapping
    public ResponseEntity<Page<ReservationDTO>> getReservations(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Reservation> reservations = reservationService.getReservationsByPeriod(startDate, endDate, page, size);
        Page<ReservationDTO> reservationDTOs = reservations.map(ReservationMapper::reservation2ReservationDTO);
        return ResponseEntity.ok(reservationDTOs);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ReservationDTO>> getReservationsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Reservation> reservations = reservationService.getReservationsByUser(userId, page, size);
        Page<ReservationDTO> reservationDTOs = reservations.map(ReservationMapper::reservation2ReservationDTO);
        return ResponseEntity.ok(reservationDTOs);
    }
}
