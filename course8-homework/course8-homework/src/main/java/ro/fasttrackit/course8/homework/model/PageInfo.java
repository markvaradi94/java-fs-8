package ro.fasttrackit.course8.homework.model;

import lombok.Builder;

@Builder
public record PageInfo(
        int totalPages,
        int totalElements,
        int crtPage,
        int pageSize
) {
}
