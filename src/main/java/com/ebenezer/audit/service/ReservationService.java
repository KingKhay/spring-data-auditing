package com.ebenezer.audit.service;

import com.ebenezer.audit.dto.ReservationCreationResultJson;
import com.ebenezer.audit.dto.ReservationInputJson;
import com.ebenezer.audit.dto.ReservationUpdateResultJson;

import java.util.UUID;

public interface ReservationService {

    ReservationCreationResultJson createReservation(ReservationInputJson reservation);

    ReservationUpdateResultJson updateReservation(UUID id, ReservationInputJson reservation);
}
