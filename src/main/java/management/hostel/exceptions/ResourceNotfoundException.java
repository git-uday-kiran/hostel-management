package management.hostel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "resource not found")
public class ResourceNotfoundException extends RuntimeException {
	public ResourceNotfoundException(String message) {
		super(message);
	}

	public ResourceNotfoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
