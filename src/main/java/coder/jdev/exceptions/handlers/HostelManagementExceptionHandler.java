package coder.jdev.exceptions.handlers;

import coder.jdev.exceptions.HostelManagementException;
import coder.jdev.utils.Utils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.context.request.WebRequest;

@Configuration
public interface HostelManagementExceptionHandler {
	//	@ExceptionHandler(HostelManagementException.class)
	default ProblemDetail handle(HostelManagementException exception, WebRequest request) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		problemDetail.setTitle("something went wrong");
		problemDetail.setDetail(exception.getMessage());
		problemDetail.setProperty("errors", Utils.getErrorMessageList(exception));
		return problemDetail;
	}
}
