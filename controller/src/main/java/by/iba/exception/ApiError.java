package by.iba.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ApiError {

    private String nameError;
    private String message;
    private Integer status;
}
