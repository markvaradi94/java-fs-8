package ro.fasttrackit.course8.homework.model.mapper;

import org.springframework.stereotype.Component;
import ro.fasttrackit.course8.homework.model.domain.ReviewDto;
import ro.fasttrackit.course8.homework.model.entity.ReviewEntity;
import ro.fasttrackit.course8.homework.model.entity.RoomEntity;

import static java.util.Optional.ofNullable;

@Component
public class ReviewMapper implements ModelMapper<ReviewEntity, ReviewDto> {
    @Override
    public ReviewDto toDto(ReviewEntity source) {
        return ReviewDto.builder()
                .id(source.getId())
                .message(source.getMessage())
                .tourist(source.getTourist())
                .rating(source.getRating())
                .roomNumber(ofNullable(source.getRoom())
                        .map(RoomEntity::getNumber)
                        .orElse(null))
                .hotelName(ofNullable(source.getRoom())
                        .map(RoomEntity::getHotelName)
                        .orElse(null))
                .build();
    }

    @Override
    public ReviewEntity toEntity(ReviewDto source) {
        return ReviewEntity.builder()
                .id(source.id())
                .message(source.message())
                .rating(source.rating())
                .tourist(source.tourist())
                .build();
    }
}
