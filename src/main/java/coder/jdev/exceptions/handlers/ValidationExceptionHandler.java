package coder.jdev.exceptions.handlers;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface ValidationExceptionHandler {

    @ExceptionHandler(SQLException.class)
    default ResponseEntity<ProblemDetail> handleSQLException(SQLException exception) {
        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage())).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    default ResponseEntity<ProblemDetail> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage())).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    default ResponseEntity<Map<String, Object>> constraintViolationException(ConstraintViolationException exception) {
        Map<String, Object> response = new HashMap<>();

        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            final String key = snakeCase(violation.getPropertyPath().toString()), value = violation.getMessage();
            errors.put(key, value);
        }

        return getMapResponseEntity(response, errors);
    }

    @ExceptionHandler(BindException.class)
    default ResponseEntity<Map<String, Object>> bindException(BindException exception) {
        Map<String, Object> response = new HashMap<>();

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : exception.getFieldErrors()) {
            final String key = snakeCase(fieldError.getField()), value = fieldError.getDefaultMessage();
            errors.put(key, value);
        }

        return getMapResponseEntity(response, errors);
    }

    private ResponseEntity<Map<String, Object>> getMapResponseEntity(Map<String, Object> response, Map<String, String> errors) {
        response.put("type", "about:blank");
        response.put("title", "Bad Request");
        response.put("status", HttpStatus.BAD_REQUEST.value());

        response.put("errors", errors);
        response.put("instance", "/api/users/add");
        response.put("properties", null);

        return ResponseEntity.badRequest().body(response);
    }

     private String snakeCase(final String input) {
        return PropertyNamingStrategies.SnakeCaseStrategy.INSTANCE.translate(input);
    }
}

