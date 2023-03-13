package ro.fasttrackit.course8.homework.model.domain;

import lombok.Builder;

@Builder
public record ReviewDto(
        Long id,
        String message,
        Integer rating,
        String tourist,
        String roomNumber,
        String hotelName
) {
}
