package by.iba.exception;

import by.iba.security.jwt.JwtAuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {

    @InitBinder
    private void activateDirectFieldAccess(DataBinder dataBinder) {
        dataBinder.initDirectFieldAccess();
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<Object> handleServiceException(
            ServiceException ex) {
        ApiError apiError = new ApiError("ServiceException", ex.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex) {
        ApiError apiError = new ApiError("ResourceNotFoundException", ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(
            AuthenticationException ex) {
        ApiError apiError = new ApiError("Error in authentication", "Check your authentication parameters", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({JwtAuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(
            JwtAuthenticationException ex) {
        ApiError apiError = new ApiError("Error in authorization with token", "Check your authorization parameters", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(
            UsernameNotFoundException ex) {
        ApiError apiError = new ApiError("Error in authorization ", "Check your authorization parameters", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalRequestException.class})
    public ResponseEntity<Object> handleIllegalRequestException(
            IllegalRequestException ex) {
        List<ApiError> errorList = new ArrayList<>();
        for (FieldError e : ex.getErrors()) {
            errorList.add(new ApiError("Request parameter " + e.getField()
                    +" is incorrect", e.getDefaultMessage(), HttpStatus.BAD_REQUEST));
        }
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = new ApiError("Method Argument Not Valid", errors.toString(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
