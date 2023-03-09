package ro.fasttrackit.curs8.course8.code.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs8.course8.code.model.entity.Customer;
import ro.fasttrackit.curs8.course8.code.model.entity.Review;
import ro.fasttrackit.curs8.course8.code.model.filters.CustomerFilters;
import ro.fasttrackit.curs8.course8.code.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAll(CustomerFilters filters) {
        return customerService.getAll(filters);
    }

    @GetMapping("filter")
    public List<Customer> getAllByCity(@RequestParam String city) {
        return customerService.getAllByCity(city);
    }

    @GetMapping("{customerId}/reviews")
    public List<Review> getReviewsForCustomer(@PathVariable Long customerId) {
        return customerService.getReviewsForCustomer(customerId);
    }
}
