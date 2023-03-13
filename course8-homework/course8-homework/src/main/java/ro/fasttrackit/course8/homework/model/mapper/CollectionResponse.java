package ro.fasttrackit.course8.homework.model.mapper;

import lombok.Builder;
import ro.fasttrackit.course8.homework.model.PageInfo;

import java.util.List;

@Builder
public record CollectionResponse<T>(
        List<T> content,
        PageInfo pageInfo
) {
}
