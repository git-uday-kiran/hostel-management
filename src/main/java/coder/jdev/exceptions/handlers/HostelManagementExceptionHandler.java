package coder.jdev.exceptions.handlers;

import coder.jdev.exceptions.HostelManagementException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Configuration
public interface HostelManagementExceptionHandler {
    @ExceptionHandler(HostelManagementException.class)
    default ResponseEntity<ProblemDetail> handle(HostelManagementException exception, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }
}
