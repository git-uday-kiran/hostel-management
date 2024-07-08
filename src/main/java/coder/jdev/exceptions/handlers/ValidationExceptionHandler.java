package coder.jdev.exceptions.handlers;

import coder.jdev.utils.ProblemDetailJson;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public interface ValidationExceptionHandler {

	@ExceptionHandler({SQLIntegrityConstraintViolationException.class})
	default ProblemDetailJson sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
		ProblemDetailJson body = ProblemDetailJson.forStatus(HttpStatus.CONFLICT);
		body.setTitle("SQL Constraint Violation");
		body.setDetail(exception.getMessage());
		body.put("sql_state", exception.getSQLState());
		body.put("error_code", exception.getErrorCode());
		return body;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	default ProblemDetailJson handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		ProblemDetailJson body = ProblemDetailJson.forStatus(HttpStatus.BAD_REQUEST);
		body.setDetail("validation failed on some fields");
		body.setTitle("Validation Failed");

		List<Object> jsonFieldErrors = new ArrayList<>();
		body.put("field_errors", jsonFieldErrors);

		for (FieldError fieldError : exception.getFieldErrors()) {
			Map<String, Object> jsonFieldError = new HashMap<>();
			jsonFieldErrors.add(jsonFieldError);
			jsonFieldError.put("field", fieldError.getField());
			jsonFieldError.put("message", fieldError.getDefaultMessage());
			jsonFieldError.put("rejected_value", fieldError.getRejectedValue());
			jsonFieldError.put("type_mismatch", fieldError.isBindingFailure());
		}
		return body;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	default ResponseEntity<ProblemDetail> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
		return ResponseEntity.of(
			ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage())
		).build();
	}

	@ExceptionHandler(BindException.class)
	default ResponseEntity<Map<String, Object>> bindException(BindException exception) {
		Map<String, Object> errors = new HashMap<>();
		for (FieldError fieldError : exception.getFieldErrors()) {
			final String key = snakeCase(fieldError.getField()), value = fieldError.getDefaultMessage();
			errors.put(key, value);
		}
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setDetail("constraint violation occurred on some fields");
		problemDetail.setTitle("constraint violation");
		problemDetail.setProperties(errors);
		return ResponseEntity.of(problemDetail).build();
	}

	private String snakeCase(final String input) {
		return PropertyNamingStrategies.SnakeCaseStrategy.INSTANCE.translate(input);
	}
}

