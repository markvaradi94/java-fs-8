package ro.fasttrackit.course8.homework.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "cleanups")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CleanupEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private LocalDateTime date;

    @ManyToOne
    private RoomEntity room;
}
