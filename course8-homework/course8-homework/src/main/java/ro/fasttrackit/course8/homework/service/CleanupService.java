package ro.fasttrackit.course8.homework.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.course8.homework.model.entity.CleanupEntity;
import ro.fasttrackit.course8.homework.repository.CleanupRepo;
import ro.fasttrackit.course8.homework.service.validator.RoomValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CleanupService {
    private final CleanupRepo repo;
    private final RoomValidator validator;

    public List<CleanupEntity> findAllByRoomId(Long roomId) {
        validator.validateExistsOrThrow(roomId);
        return repo.findAllByRoomId(roomId);
    }

    @Transactional
    public void deleteCleanupsForRoom(Long roomId) {
        validator.validateExistsOrThrow(roomId);
        repo.deleteAllByRoomId(roomId);
    }
}
