package com.ebenezer.audit.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationCreationResultJson(UUID id, String status, String location, LocalDateTime createdAt) {
}
