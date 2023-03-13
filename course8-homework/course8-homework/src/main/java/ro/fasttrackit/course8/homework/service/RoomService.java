package ro.fasttrackit.course8.homework.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.fasttrackit.course8.homework.exception.ResourceNotFoundException;
import ro.fasttrackit.course8.homework.model.entity.CleanupEntity;
import ro.fasttrackit.course8.homework.model.entity.RoomEntity;
import ro.fasttrackit.course8.homework.model.filters.RoomFilters;
import ro.fasttrackit.course8.homework.repository.RoomRepo;
import ro.fasttrackit.course8.homework.repository.dao.RoomDao;
import ro.fasttrackit.course8.homework.service.validator.RoomValidator;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final CleanupService cleanupService;
    private final ReviewService reviewService;
    private final RoomRepo repo;
    private final RoomDao dao;
    private final RoomValidator validator;
    private final ObjectMapper mapper;

    public Page<RoomEntity> getAll(RoomFilters filters, Pageable pageable) {
        return dao.getAll(filters, ofNullable(pageable).orElseGet(Pageable::unpaged));
    }

    public Optional<RoomEntity> findById(Long id) {
        return repo.findById(id);
    }

    public RoomEntity addRoom(RoomEntity newRoom) {
        validator.validateNewThrow(newRoom);
        return repo.save(newRoom);
    }

    public Optional<RoomEntity> deleteRoom(Long id) {
        validator.validateExistsOrThrow(id);
        Optional<RoomEntity> roomOptional = repo.findById(id);
        roomOptional.ifPresent(this::deleteAllDataForRooms);
        return roomOptional;
    }

    @Transactional
    private void deleteAllDataForRooms(RoomEntity room) {
        cleanupService.deleteCleanupsForRoom(room.getId());
        reviewService.deleteReviewsForRoom(room.getId());
        repo.delete(room);
    }

    @SneakyThrows
    public RoomEntity patchRoom(Long id, JsonPatch patch) {
        validator.validateExistsOrThrow(id);
        RoomEntity roomToPatch = getOrThrow(id);
        JsonNode patchedRoomJson = patch.apply(mapper.valueToTree(roomToPatch));
        RoomEntity patchedRoom = mapper.treeToValue(patchedRoomJson, RoomEntity.class);
        return repo.save(RoomEntity.builder()
                .id(patchedRoom.getId())
                .floor(patchedRoom.getFloor())
                .hotelName(patchedRoom.getHotelName())
                .number(patchedRoom.getNumber())
                .facilities(patchedRoom.getFacilities())
                .build());
    }

    public List<CleanupEntity> getCleanupsForRoom(Long roomId) {
        validator.validateExistsOrThrow(roomId);
        return cleanupService.findAllByRoomId(roomId);
    }

    private RoomEntity getOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find room with id " + id));
    }
}
