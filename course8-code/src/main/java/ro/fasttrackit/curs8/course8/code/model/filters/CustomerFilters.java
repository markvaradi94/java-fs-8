package ro.fasttrackit.curs8.course8.code.model.filters;

import lombok.Builder;

import java.util.List;

@Builder
public record CustomerFilters(
        String name,
        Integer age,
        List<String> city
) {
}
