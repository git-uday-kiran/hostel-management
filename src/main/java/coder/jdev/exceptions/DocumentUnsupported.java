package coder.jdev.exceptions;

public class DocumentUnsupported extends HostelManagementException {
	public DocumentUnsupported(String message) {
		super(message);
	}

	public DocumentUnsupported(String message, Throwable throwable) {
		super(message, throwable);
	}
}
