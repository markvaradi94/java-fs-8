package ro.fasttrackit.curs8.course8.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.fasttrackit.curs8.course8.code.model.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByAddressCityContainingIgnoreCase(String city);

    List<Customer> findByAgeGreaterThan(int age);

    @Query("SELECT c from Customer c WHERE c.age >= :age")
    List<Customer> findOldPersonsJpql(int age);

    @Query(value = "SELECT * from Customer WHERE age >= :age", nativeQuery = true)
    List<Customer> findPersonsSql(int age);
}
