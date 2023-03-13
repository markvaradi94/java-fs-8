package ro.fasttrackit.course8.homework.model.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CleaningProcedureDto(
        Long id,
        String name,
        Integer outcome,
        LocalDateTime date
) {
}
