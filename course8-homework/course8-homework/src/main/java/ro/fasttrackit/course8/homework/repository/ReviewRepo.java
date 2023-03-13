package ro.fasttrackit.course8.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.course8.homework.model.entity.ReviewEntity;

public interface ReviewRepo extends JpaRepository<ReviewEntity, Long> {
    void deleteAllByRoomId(Long roomId);
}
