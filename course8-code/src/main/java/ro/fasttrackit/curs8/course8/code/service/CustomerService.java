package ro.fasttrackit.curs8.course8.code.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs8.course8.code.model.entity.Customer;
import ro.fasttrackit.curs8.course8.code.model.entity.Review;
import ro.fasttrackit.curs8.course8.code.model.filters.CustomerFilters;
import ro.fasttrackit.curs8.course8.code.repository.CustomerDao;
import ro.fasttrackit.curs8.course8.code.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDao dao;
    private final ReviewService reviewService;

    public List<Customer> getAll(CustomerFilters filters) {
        return dao.getAll(filters);
    }

    public List<Customer> getAllByCity(String city) {
        return customerRepository.findByAddressCityContainingIgnoreCase(city);
    }

    public List<Review> getReviewsForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Could not find customer with id: " + customerId));
        return reviewService.getReviewsForCustomer(customer.getId());
    }
}
