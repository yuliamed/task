package by.iba.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;
@Getter
@AllArgsConstructor
public class IllegalRequestException extends RuntimeException {
    private final List<FieldError> errors;

}
