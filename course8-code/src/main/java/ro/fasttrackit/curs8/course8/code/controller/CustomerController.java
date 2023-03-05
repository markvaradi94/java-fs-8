package ro.fasttrackit.curs8.course8.code.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.curs8.course8.code.model.entity.Customer;
import ro.fasttrackit.curs8.course8.code.model.entity.Review;
import ro.fasttrackit.curs8.course8.code.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("{customerId}/reviews")
    public List<Review> getReviewsForCustomer(@PathVariable Long customerId) {
        return customerService.getReviewsForCustomer(customerId);
    }
}
