package ro.fasttrackit.course8.homework.model.mapper;

import org.springframework.stereotype.Component;
import ro.fasttrackit.course8.homework.model.domain.RoomFacilitiesDto;
import ro.fasttrackit.course8.homework.model.entity.RoomFacilitiesEntity;

import java.util.Random;

import static java.util.Optional.ofNullable;

@Component
public class RoomFacilitiesMapper implements ModelMapper<RoomFacilitiesEntity, RoomFacilitiesDto> {
    @Override
    public RoomFacilitiesDto toDto(RoomFacilitiesEntity source) {
        return RoomFacilitiesDto.builder()
                .id(source.getId())
                .tv(source.isTv())
                .doubleBed(source.isDoubleBed())
                .build();
    }

    @Override
    public RoomFacilitiesEntity toEntity(RoomFacilitiesDto source) {
        return RoomFacilitiesEntity.builder()
                .id(ofNullable(source.id())
                        .orElse(new Random().nextLong()))
                .tv(source.tv())
                .doubleBed(source.doubleBed())
                .build();
    }
}
