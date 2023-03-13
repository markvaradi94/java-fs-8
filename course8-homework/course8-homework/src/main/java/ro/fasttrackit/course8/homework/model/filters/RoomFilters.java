package ro.fasttrackit.course8.homework.model.filters;

import lombok.Builder;

@Builder
public record RoomFilters(
        String number,
        Integer floor,
        String hotelName,
        Boolean tv,
        Boolean doubleBed
) {
}
