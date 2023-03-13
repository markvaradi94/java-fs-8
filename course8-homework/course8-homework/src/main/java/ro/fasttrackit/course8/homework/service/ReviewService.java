package ro.fasttrackit.course8.homework.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.course8.homework.repository.ReviewRepo;
import ro.fasttrackit.course8.homework.service.validator.RoomValidator;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepo repo;
    private final RoomValidator validator;

    @Transactional
    public void deleteReviewsForRoom(Long roomId) {
        validator.validateExistsOrThrow(roomId);
        repo.deleteAllByRoomId(roomId);
    }
}
