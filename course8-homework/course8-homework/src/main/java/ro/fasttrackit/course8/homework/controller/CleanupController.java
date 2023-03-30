package ro.fasttrackit.course8.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.course8.homework.model.PageInfo;
import ro.fasttrackit.course8.homework.model.domain.CleanupDto;
import ro.fasttrackit.course8.homework.model.entity.CleanupEntity;
import ro.fasttrackit.course8.homework.model.filters.CleanupFilters;
import ro.fasttrackit.course8.homework.model.mapper.CleanupMapper;
import ro.fasttrackit.course8.homework.model.mapper.CollectionResponse;
import ro.fasttrackit.course8.homework.service.CleanupService;

@RestController
@RequestMapping("cleanups")
@RequiredArgsConstructor
public class CleanupController {
    private final CleanupService cleanupService;
    private final CleanupMapper cleanupMapper;

    @GetMapping
    public CollectionResponse<CleanupDto> getAllCleanups(CleanupFilters filters, Pageable pageable) {
        Page<CleanupEntity> cleanupsPage = cleanupService.findAll(filters, pageable);
        return CollectionResponse.<CleanupDto>builder()
                .content(cleanupMapper.toDto(cleanupsPage.getContent()))
                .pageInfo(PageInfo.builder()
                        .totalPages(cleanupsPage.getTotalPages())
                        .totalElements(cleanupsPage.getNumberOfElements())
                        .pageSize(pageable.getPageSize())
                        .crtPage(pageable.getPageNumber())
                        .build())
                .build();
    }
}
