package ro.fasttrackit.course8.homework.model.filters;

import lombok.Builder;

@Builder
public record CleanupFilters(
        Long id,
        Long roomId,
        String number,
        Integer floor,
        String hotelName
) {
}
