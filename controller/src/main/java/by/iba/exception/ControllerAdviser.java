package by.iba.exception;

import by.iba.security.jwt.JwtAuthenticationException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
        ApiError apiError = new ApiError("ServiceException", ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex) {
        ApiError apiError = new ApiError("ResourceNotFoundException", ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AuthenticationException.class, JwtAuthenticationException.class, UsernameNotFoundException.class})
    public ResponseEntity<Object> handleAuthenticationException(
            AuthenticationException ex) {
        ApiError apiError = new ApiError("Error in authentication", "Check your authentication parameters", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({JwtException.class})
    public ResponseEntity<Object> handleJwtAuthenticationException(
            JwtException ex) {
        ApiError apiError = new ApiError("Error in authorization with token", "Check your authorization parameters", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(
            UsernameNotFoundException ex) {
        ApiError apiError = new ApiError("Error in authorization", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalRequestException.class})
    public ResponseEntity<Object> handleIllegalRequestException(
            IllegalRequestException ex) {
        List<ApiError> errorList = new ArrayList<>();
        for (FieldError e : ex.getErrors()) {
            errorList.add(new ApiError("Request parameter " + e.getField()
                    + " is incorrect", e.getDefaultMessage(), HttpStatus.BAD_REQUEST.value()));
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
        ApiError apiError = new ApiError("Method Argument Not Valid", errors.toString(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError("Method Argument Not Valid", "You have sent empty body", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(
                " method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        ApiError apiError = new ApiError(
                ex.getLocalizedMessage(), builder.toString(), HttpStatus.METHOD_NOT_ALLOWED.value());
        return new ResponseEntity(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

}
