package com.ebenezer.audit.controller;

import com.ebenezer.audit.dto.ReservationCreationResultJson;
import com.ebenezer.audit.dto.ReservationInputJson;
import com.ebenezer.audit.dto.ReservationUpdateResultJson;
import com.ebenezer.audit.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/")
    ResponseEntity<ReservationCreationResultJson> createReservation(@RequestBody ReservationInputJson reservationInputJson){
        return new ResponseEntity<>(reservationService.createReservation(reservationInputJson), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<ReservationUpdateResultJson> updateResultJsonResponseEntity(@PathVariable UUID id, @RequestBody ReservationInputJson reservationInputJson){
        return ResponseEntity.ok(reservationService.updateReservation(id, reservationInputJson));
    }
}
