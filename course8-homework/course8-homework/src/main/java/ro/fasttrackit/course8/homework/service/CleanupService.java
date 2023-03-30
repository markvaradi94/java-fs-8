package ro.fasttrackit.course8.homework.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.fasttrackit.course8.homework.model.entity.CleanupEntity;
import ro.fasttrackit.course8.homework.model.filters.CleanupFilters;
import ro.fasttrackit.course8.homework.repository.CleanupRepo;
import ro.fasttrackit.course8.homework.repository.dao.CleanupDao;
import ro.fasttrackit.course8.homework.service.validator.RoomValidator;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class CleanupService {
    private final CleanupRepo repo;
    private final CleanupDao dao;
    private final RoomValidator validator;

    public CleanupEntity addCleanup(CleanupEntity cleanup) {
        validator.validateExistsOrThrow(cleanup.getId());
        return repo.save(cleanup);
    }

    public List<CleanupEntity> findAllByRoomId(Long roomId) {
        validator.validateExistsOrThrow(roomId);
        return repo.findAllByRoomId(roomId);
    }

    @Transactional
    public void deleteCleanupsForRoom(Long roomId) {
        validator.validateExistsOrThrow(roomId);
        repo.deleteAllByRoomId(roomId);
    }

    public Page<CleanupEntity> findAll(CleanupFilters filters, Pageable pageable) {
        return dao.getAll(filters, ofNullable(pageable).orElseGet(Pageable::unpaged));
    }
}
