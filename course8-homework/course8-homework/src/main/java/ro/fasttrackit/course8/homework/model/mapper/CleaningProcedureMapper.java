package ro.fasttrackit.course8.homework.model.mapper;

import org.springframework.stereotype.Component;
import ro.fasttrackit.course8.homework.model.domain.CleaningProcedureDto;
import ro.fasttrackit.course8.homework.model.entity.CleaningProcedureEntity;
import ro.fasttrackit.course8.homework.model.entity.CleanupEntity;

import static java.util.Optional.ofNullable;

@Component
public class CleaningProcedureMapper implements ModelMapper<CleaningProcedureEntity, CleaningProcedureDto> {
    @Override
    public CleaningProcedureDto toDto(CleaningProcedureEntity source) {
        return CleaningProcedureDto.builder()
                .id(source.getId())
                .date(ofNullable(source.getCleanup())
                        .map(CleanupEntity::getDate)
                        .orElse(null))
                .name(source.getName())
                .outcome(source.getOutcome())
                .build();
    }

    @Override
    public CleaningProcedureEntity toEntity(CleaningProcedureDto source) {
        return CleaningProcedureEntity.builder()
                .id(source.id())
                .outcome(source.outcome())
                .name(source.name())
                .build();
    }
}
