package ro.fasttrackit.curs8.course8.code.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs8.course8.code.model.entity.Review;
import ro.fasttrackit.curs8.course8.code.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> getReviewsForCustomer(Long customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }
}
