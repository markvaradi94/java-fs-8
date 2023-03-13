package ro.fasttrackit.course8.homework.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "facilities")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomFacilitiesEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private boolean tv;
    private boolean doubleBed;
}
