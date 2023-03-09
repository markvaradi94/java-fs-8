package ro.fasttrackit.curs8.course8.code.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs8.course8.code.model.entity.*;
import ro.fasttrackit.curs8.course8.code.repository.CustomerRepository;
import ro.fasttrackit.curs8.course8.code.repository.ReviewRepository;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CustomerRepository customerRepo;
    private final ReviewRepository reviewRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Customer> customers = customerRepo.saveAll(List.of(
                Customer.builder()
                        .name("Gyuszi")
                        .age(25)
                        .address(Address.builder()
                                .street("Unirii")
                                .city("Oradea")
                                .build())
                        .orders(List.of(
                                ItemOrder.builder()
                                        .description("order1")
                                        .items(List.of(
                                                Item.builder()
                                                        .name("TV")
                                                        .build(),
                                                Item.builder()
                                                        .name("Phone")
                                                        .build(),
                                                Item.builder()
                                                        .name("Monitor")
                                                        .build()))
                                        .build(),
                                ItemOrder.builder()
                                        .description("order2")
                                        .items(List.of(
                                                Item.builder()
                                                        .name("Soap")
                                                        .build()))
                                        .build()))
                        .build(),
                Customer.builder()
                        .name("Pityu")
                        .age(73)
                        .address(Address.builder()
                                .street("Decebal")
                                .city("Oradea")
                                .build())
                        .orders(List.of(
                                ItemOrder.builder()
                                        .description("order3")
                                        .items(List.of(Item.builder()
                                                .name("Bread")
                                                .build()))
                                        .build(),
                                ItemOrder.builder()
                                        .description("order4")
                                        .items(List.of(
                                                Item.builder()
                                                        .name("Cheese")
                                                        .build(),
                                                Item.builder()
                                                        .name("Tomatoes")
                                                        .build(),
                                                Item.builder()
                                                        .name("Wine")
                                                        .build()))
                                        .build()
                        ))
                        .build(),
                Customer.builder()
                        .name("Zsuzsi")
                        .age(44)
                        .address(Address.builder()
                                .street("Motilor")
                                .city("Cluj")
                                .build())
                        .build(),
                Customer.builder()
                        .name("Bela")
                        .age(30)
                        .address(Address.builder()
                                .street("Principala")
                                .city("Bors")
                                .build())
                        .build()
        ));

        reviewRepository.saveAll(List.of(
                Review.builder()
                        .customer(customers.get(0))
                        .rating(3)
                        .message("Alright")
                        .build(),
                Review.builder()
                        .customer(customers.get(1))
                        .rating(5)
                        .message("Excellent")
                        .build(),
                Review.builder()
                        .customer(customers.get(2))
                        .rating(4)
                        .message("Very good")
                        .build(),
                Review.builder()
                        .customer(customers.get(0))
                        .rating(5)
                        .message("Superb")
                        .build(),
                Review.builder()
                        .customer(customers.get(1))
                        .rating(2)
                        .message("Meh")
                        .build(),
                Review.builder()
                        .customer(customers.get(2))
                        .rating(1)
                        .message("Awful")
                        .build()
        ));

        System.out.println("----------------------------QUERY-------------------------------");
        System.out.println("----------- Spring Data Methods -------------------");
        printResult(() -> customerRepo.findByAgeGreaterThan(40));
        System.out.println("---------------- JPQL ---------------");
        printResult(() -> customerRepo.findOldPersonsJpql(60));
    }

    private void printResult(Supplier<List<Customer>> customerSupplier) {
        System.out.println(customerSupplier.get().stream()
                .map(Customer::getName)
                .collect(Collectors.joining(", ")));
    }
}
