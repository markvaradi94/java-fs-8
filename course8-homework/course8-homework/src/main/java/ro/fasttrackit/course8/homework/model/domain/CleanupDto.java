package ro.fasttrackit.course8.homework.model.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CleanupDto(
        Long id,
        LocalDateTime date,
        String roomNumber,
        Integer floor,
        String hotelName
) {
}
