package ro.fasttrackit.course8.homework.model.mapper;

import java.util.Collection;
import java.util.List;

public interface ModelMapper<E, D> {

    D toDto(E source);

    E toEntity(D source);

    default List<D> toDto(Collection<E> source) {
        return source.stream()
                .map(this::toDto)
                .toList();
    }

    default List<E> toEntity(Collection<D> source) {
        return source.stream()
                .map(this::toEntity)
                .toList();
    }
}
