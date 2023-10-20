package coder.jdev.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface ApplicationExceptionHandler {
    @ExceptionHandler(Throwable.class)
    default ResponseEntity<ProblemDetail> handle(Throwable throwable) {
        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage())).build();
    }
}
