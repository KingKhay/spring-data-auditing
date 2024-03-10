package com.ebenezer.audit.repository;

import com.ebenezer.audit.exception.GenericNotFoundException;
import com.ebenezer.audit.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    default Reservation findReservationById(UUID id){
        return findById(id)
                .orElseThrow(() -> new GenericNotFoundException(String.format("Reservation with id %s not found", id)));
    }
}
