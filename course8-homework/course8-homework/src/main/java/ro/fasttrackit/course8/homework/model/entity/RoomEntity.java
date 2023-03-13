package ro.fasttrackit.course8.homework.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "rooms")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String number;
    private int floor;
    private String hotelName;

    @OneToOne(cascade = PERSIST)
    private RoomFacilitiesEntity facilities;
}
