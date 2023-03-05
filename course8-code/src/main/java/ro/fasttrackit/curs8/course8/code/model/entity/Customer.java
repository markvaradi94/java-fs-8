package ro.fasttrackit.curs8.course8.code.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private int age;

    @OneToOne(cascade = PERSIST)
    private Address address;

    @OneToMany(cascade = PERSIST)
    private List<ItemOrder> orders;
}
