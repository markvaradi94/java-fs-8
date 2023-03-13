package ro.fasttrackit.course8.homework.model.mapper;

import org.springframework.stereotype.Component;
import ro.fasttrackit.course8.homework.model.domain.CleanupDto;
import ro.fasttrackit.course8.homework.model.entity.CleanupEntity;
import ro.fasttrackit.course8.homework.model.entity.RoomEntity;

import static java.util.Optional.ofNullable;

@Component
public class CleanupMapper implements ModelMapper<CleanupEntity, CleanupDto> {
    @Override
    public CleanupDto toDto(CleanupEntity source) {
        return CleanupDto.builder()
                .id(source.getId())
                .date(source.getDate())
                .hotelName(ofNullable(source.getRoom())
                        .map(RoomEntity::getHotelName)
                        .orElse(null))
                .roomNumber(ofNullable(source.getRoom())
                        .map(RoomEntity::getNumber)
                        .orElse(null))
                .floor(ofNullable(source.getRoom())
                        .map(RoomEntity::getFloor)
                        .orElse(null))
                .build();
    }

    @Override
    public CleanupEntity toEntity(CleanupDto source) {
        return CleanupEntity.builder()
                .id(source.id())
                .date(source.date())
                .room(RoomEntity.builder()
                        .hotelName(source.hotelName())
                        .number(source.roomNumber())
                        .floor(source.floor())
                        .build())
                .build();
    }
}
