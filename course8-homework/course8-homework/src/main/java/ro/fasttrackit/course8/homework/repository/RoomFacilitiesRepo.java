package ro.fasttrackit.course8.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.course8.homework.model.entity.RoomFacilitiesEntity;

public interface RoomFacilitiesRepo extends JpaRepository<RoomFacilitiesEntity, Long> {
}
