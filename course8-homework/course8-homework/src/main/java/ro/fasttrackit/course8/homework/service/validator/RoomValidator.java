package ro.fasttrackit.course8.homework.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.course8.homework.exception.ValidationException;
import ro.fasttrackit.course8.homework.model.entity.RoomEntity;
import ro.fasttrackit.course8.homework.repository.RoomRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.String.join;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@Component
@RequiredArgsConstructor
public class RoomValidator {
    private final RoomRepo repo;

    public void validateNewThrow(RoomEntity newRoom) {
        validate(newRoom, true)
                .ifPresent(validationException -> {
                    throw validationException;
                });
    }

    public void validateReplaceThrow(Long roomId, RoomEntity updatedRoom) {
        exists(roomId)
                .or(() -> validate(updatedRoom, false))
                .ifPresent(validationException -> {
                    throw validationException;
                });
    }

    private Optional<ValidationException> validate(RoomEntity room, boolean newRoom) {
        List<String> errorMessages = new ArrayList<>();
        if (isNullOrEmpty(room.getNumber())) {
            errorMessages.add("Room number cannot be null or empty.");
        } else if (newRoom && repo.existsByNumber(room.getNumber())) {
            errorMessages.add("Room number cannot be duplicate.");
        } else if (!newRoom && repo.existsByNumberAndIdNot(room.getNumber(), room.getId())) {
            errorMessages.add("Room number cannot be duplicate.");
        }
        return errorMessages.isEmpty()
                ? Optional.empty()
                : of(new ValidationException(join(", ", errorMessages)));
    }

    public void validateExistsOrThrow(Long roomId) {
        exists(roomId)
                .ifPresent(validationException -> {
                    throw validationException;
                });
    }

    private Optional<ValidationException> exists(Long roomId) {
        return repo.existsById(roomId)
                ? empty()
                : of(new ValidationException("Room with id "));
    }
}
