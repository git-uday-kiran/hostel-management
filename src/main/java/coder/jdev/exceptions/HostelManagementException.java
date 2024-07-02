package coder.jdev.exceptions;

public abstract class HostelManagementException extends RuntimeException {

	public HostelManagementException(final String message) {
		super(message);
	}

	public HostelManagementException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
