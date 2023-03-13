package ro.fasttrackit.course8.homework.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "procedures")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CleaningProcedureEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String name;
    private int outcome;

    @ManyToOne
    private CleanupEntity cleanup;
}
