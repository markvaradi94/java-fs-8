package ro.fasttrackit.course8.homework.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "reviews")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String message;
    private int rating;
    private String tourist;

    @ManyToOne
    private RoomEntity room;
}
