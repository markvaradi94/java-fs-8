package ro.fasttrackit.course8.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.course8.homework.model.entity.RoomEntity;

public interface RoomRepo extends JpaRepository<RoomEntity, Long> {
    boolean existsByNumber(String number);

    boolean existsByNumberAndIdNot(String number, Long id);
}
