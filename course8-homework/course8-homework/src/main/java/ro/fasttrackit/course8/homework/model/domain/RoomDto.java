package ro.fasttrackit.course8.homework.model.domain;

import lombok.Builder;

@Builder
public record RoomDto(
        Long id,
        String number,
        Integer floor,
        String hotelName,
        RoomFacilitiesDto facilities
) {
}
