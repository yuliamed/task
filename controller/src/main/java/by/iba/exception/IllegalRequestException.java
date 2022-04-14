package by.iba.exception;

import lombok.AllArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;
@AllArgsConstructor
public class IllegalRequestException extends RuntimeException {
    private final List<FieldError> errors;

}
