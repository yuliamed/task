package by.iba.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiError {
    private String nameError;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ApiError(String nameError, String message) {
        this.nameError = nameError;
        this.message = message;
    }
}
