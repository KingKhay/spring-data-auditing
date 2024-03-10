package com.ebenezer.audit.service.impl;

import com.ebenezer.audit.dto.ReservationCreationResultJson;
import com.ebenezer.audit.dto.ReservationInputJson;
import com.ebenezer.audit.dto.ReservationUpdateResultJson;
import com.ebenezer.audit.exception.GenericNotFoundException;
import com.ebenezer.audit.model.Reservation;
import com.ebenezer.audit.repository.ReservationRepository;
import com.ebenezer.audit.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ObjectMapper mapper;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ObjectMapper mapper, ReservationRepository reservationRepository) {
        this.mapper = mapper;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationCreationResultJson createReservation(ReservationInputJson reservation) {
        Reservation entityReservation = mapper.convertValue(reservation, Reservation.class);
        reservationRepository.save(entityReservation);
        return mapper.convertValue(entityReservation, ReservationCreationResultJson.class);
    }

    @Override
    public ReservationUpdateResultJson updateReservation(UUID id, ReservationInputJson reservation) {
        Reservation entityReservation = reservationRepository.findReservationById(id);
        entityReservation.setStatus(reservation.status());
        entityReservation.setLocation(reservation.location());
        reservationRepository.save(entityReservation);
        return mapper.convertValue(entityReservation, ReservationUpdateResultJson.class);
    }
}
