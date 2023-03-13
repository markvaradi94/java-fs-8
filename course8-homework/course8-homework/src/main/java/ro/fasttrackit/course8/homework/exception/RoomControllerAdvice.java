package ro.fasttrackit.course8.homework.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RoomControllerAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    ApiError handleValidationException(ValidationException exception) {
        return new ApiError(exception.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    ApiError handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ApiError(exception.getMessage());
    }
}

record ApiError(String message) {
}
