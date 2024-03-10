package com.ebenezer.audit.dto;

import java.util.UUID;

public record ReservationUpdateResultJson(UUID id, String status, String location) {
}
