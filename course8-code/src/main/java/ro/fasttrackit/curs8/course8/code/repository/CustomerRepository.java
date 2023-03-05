package ro.fasttrackit.curs8.course8.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs8.course8.code.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
