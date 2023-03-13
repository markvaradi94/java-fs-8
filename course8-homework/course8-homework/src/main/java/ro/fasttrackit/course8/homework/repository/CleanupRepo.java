package ro.fasttrackit.course8.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.course8.homework.model.entity.CleanupEntity;

import java.util.List;

public interface CleanupRepo extends JpaRepository<CleanupEntity, Long> {
    List<CleanupEntity> findAllByRoomId(Long roomId);

    void deleteAllByRoomId(Long roomId);
}
