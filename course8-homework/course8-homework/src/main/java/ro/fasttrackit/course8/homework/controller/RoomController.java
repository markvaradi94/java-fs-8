package ro.fasttrackit.course8.homework.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.course8.homework.exception.ResourceNotFoundException;
import ro.fasttrackit.course8.homework.model.PageInfo;
import ro.fasttrackit.course8.homework.model.domain.CleanupDto;
import ro.fasttrackit.course8.homework.model.domain.RoomDto;
import ro.fasttrackit.course8.homework.model.entity.RoomEntity;
import ro.fasttrackit.course8.homework.model.filters.RoomFilters;
import ro.fasttrackit.course8.homework.model.mapper.CleanupMapper;
import ro.fasttrackit.course8.homework.model.mapper.CollectionResponse;
import ro.fasttrackit.course8.homework.model.mapper.RoomMapper;
import ro.fasttrackit.course8.homework.service.RoomService;

import java.util.List;

import static org.springframework.data.domain.Pageable.unpaged;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;
    private final CleanupMapper cleanupMapper;

    @GetMapping
    public CollectionResponse<RoomDto> getAll(RoomFilters filters, Pageable pageable) {
        Page<RoomEntity> roomsPage = roomService.getAll(filters, unpaged());
        return CollectionResponse.<RoomDto>builder()
                .content(roomMapper.toDto(roomsPage.getContent()))
                .pageInfo(PageInfo.builder()
                        .totalPages(roomsPage.getTotalPages())
                        .totalElements(roomsPage.getNumberOfElements())
                        .pageSize(pageable.getPageSize())
                        .crtPage(pageable.getPageNumber())
                        .build())
                .build();
    }

    @GetMapping("{id}")
    public RoomDto getById(@PathVariable long id) {
        return roomMapper.toDto(roomService.findById(id)
                .orElseThrow());
    }

    @GetMapping("{id}/cleanups")
    public List<CleanupDto> roomCleanups(@PathVariable long id) {
        return cleanupMapper.toDto(roomService.getCleanupsForRoom(id));
    }

    @PostMapping("{id}/cleanups")
    public CleanupDto createCleanup(@RequestBody CleanupDto cleanup, long id) {
        return cleanupMapper.toDto(roomService.createCleanupForRoom(id, cleanupMapper.toEntity(cleanup)));
    }

    @PostMapping
    public RoomDto add(@RequestBody RoomDto roomDto) {
        return roomMapper.toDto(roomService.addRoom(roomMapper.toEntity(roomDto)));
    }

    @DeleteMapping("{id}")
    public RoomDto delete(@PathVariable long id) {
        return roomMapper.toDto(roomService.deleteRoom(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find room with id " + id)));
    }

    @PatchMapping("{id}")
    public RoomDto patch(@RequestBody JsonPatch patch, @PathVariable long id) {
        return roomMapper.toDto(roomService.patchRoom(id, patch));
    }
}
