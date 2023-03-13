package ro.fasttrackit.course8.homework.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.fasttrackit.course8.homework.model.entity.*;
import ro.fasttrackit.course8.homework.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CleaningProcedureRepo cleaningProcedureRepo;
    private final CleanupRepo cleanupRepo;
    private final ReviewRepo reviewRepo;
    private final RoomFacilitiesRepo roomFacilitiesRepo;
    private final RoomRepo roomRepo;


    @Override
    public void run(String... args) throws Exception {
        roomRepo.saveAll(List.of(
                RoomEntity.builder()
                        .hotelName("Iris")
                        .floor(2)
                        .number("205")
                        .facilities(RoomFacilitiesEntity.builder()
                                .doubleBed(false)
                                .tv(true)
                                .build())
                        .build(),
                RoomEntity.builder()
                        .hotelName("Iris")
                        .floor(2)
                        .number("210")
                        .facilities(RoomFacilitiesEntity.builder()
                                .doubleBed(true)
                                .tv(true)
                                .build())
                        .build(),
                RoomEntity.builder()
                        .hotelName("Iris")
                        .floor(3)
                        .number("311")
                        .facilities(RoomFacilitiesEntity.builder()
                                .doubleBed(false)
                                .tv(false)
                                .build())
                        .build(),
                RoomEntity.builder()
                        .hotelName("Astoria")
                        .floor(5)
                        .number("509")
                        .facilities(RoomFacilitiesEntity.builder()
                                .doubleBed(true)
                                .tv(true)
                                .build())
                        .build(),
                RoomEntity.builder()
                        .hotelName("Astoria")
                        .floor(3)
                        .number("301")
                        .facilities(RoomFacilitiesEntity.builder()
                                .doubleBed(true)
                                .tv(false)
                                .build())
                        .build()
        ));

        cleanupRepo.saveAll(List.of(
                CleanupEntity.builder()
                        .room(roomRepo.findById(2L).orElseThrow())
                        .date(LocalDateTime.now().minusDays(3))
                        .build(),
                CleanupEntity.builder()
                        .room(roomRepo.findById(2L).orElseThrow())
                        .date(LocalDateTime.now().minusDays(2))
                        .build(),
                CleanupEntity.builder()
                        .room(roomRepo.findById(2L).orElseThrow())
                        .date(LocalDateTime.now().minusDays(1))
                        .build(),
                CleanupEntity.builder()
                        .room(roomRepo.findById(4L).orElseThrow())
                        .date(LocalDateTime.now().minusDays(5))
                        .build(),
                CleanupEntity.builder()
                        .room(roomRepo.findById(5L).orElseThrow())
                        .date(LocalDateTime.now().minusDays(10))
                        .build()
        ));

        cleaningProcedureRepo.saveAll(List.of(
                CleaningProcedureEntity.builder()
                        .cleanup(cleanupRepo.findById(1L).orElseThrow())
                        .name("Vacuuming")
                        .outcome(10)
                        .build(),
                CleaningProcedureEntity.builder()
                        .cleanup(cleanupRepo.findById(2L).orElseThrow())
                        .name("Dusting")
                        .outcome(10)
                        .build()
        ));

        reviewRepo.saveAll(List.of(
                ReviewEntity.builder()
                        .rating(5)
                        .message("Meh service")
                        .tourist("The annoying kind of tourist")
                        .room(roomRepo.findById(2L).orElseThrow())
                        .build()
        ));
    }
}
