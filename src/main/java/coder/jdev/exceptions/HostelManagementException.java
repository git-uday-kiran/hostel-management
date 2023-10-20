package coder.jdev.exceptions;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class HostelManagementException extends RuntimeException {

    public HostelManagementException(final String message) {
        super(message);
    }

    public HostelManagementException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
