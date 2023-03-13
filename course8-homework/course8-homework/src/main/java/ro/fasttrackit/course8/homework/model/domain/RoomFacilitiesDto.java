package ro.fasttrackit.course8.homework.model.domain;

import lombok.Builder;

@Builder
public record RoomFacilitiesDto(
        Long id,
        Boolean tv,
        Boolean doubleBed
) {
}
