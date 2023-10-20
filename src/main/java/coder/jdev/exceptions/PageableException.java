package coder.jdev.exceptions;


public class PageableException extends HostelManagementException {

    public PageableException(String message) {
        super(message);
    }

    public PageableException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
