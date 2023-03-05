package ro.fasttrackit.curs8.course8.code.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs8.course8.code.model.entity.Customer;
import ro.fasttrackit.curs8.course8.code.model.entity.Review;
import ro.fasttrackit.curs8.course8.code.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ReviewService reviewService;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public List<Review> getReviewsForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Could not find customer with id: " + customerId));
        return reviewService.getReviewsForCustomer(customer.getId());
    }
}
