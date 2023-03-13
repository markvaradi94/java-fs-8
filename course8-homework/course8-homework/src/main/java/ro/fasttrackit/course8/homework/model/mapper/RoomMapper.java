package ro.fasttrackit.course8.homework.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.course8.homework.model.domain.RoomDto;
import ro.fasttrackit.course8.homework.model.entity.RoomEntity;

import java.util.Optional;
import java.util.Random;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class RoomMapper implements ModelMapper<RoomEntity, RoomDto> {
    private final RoomFacilitiesMapper facilitiesMapper;

    @Override
    public RoomDto toDto(RoomEntity source) {
        return RoomDto.builder()
                .id(source.getId())
                .number(source.getNumber())
                .floor(source.getFloor())
                .hotelName(source.getHotelName())
                .facilities(facilitiesMapper.toDto(source.getFacilities()))
                .build();
    }

    @Override
    public RoomEntity toEntity(RoomDto source) {
        return RoomEntity.builder()
                .id(ofNullable(source.id())
                        .orElse(new Random().nextLong()))
                .number(source.number())
                .floor(source.floor())
                .hotelName(source.hotelName())
                .facilities(facilitiesMapper.toEntity(source.facilities()))
                .build();
    }
}
