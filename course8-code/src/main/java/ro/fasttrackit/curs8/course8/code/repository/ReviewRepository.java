package ro.fasttrackit.curs8.course8.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs8.course8.code.model.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCustomerId(Long id);
}
