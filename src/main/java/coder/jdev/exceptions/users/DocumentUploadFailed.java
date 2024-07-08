package coder.jdev.exceptions.users;

import coder.jdev.exceptions.HostelManagementException;

public class DocumentUploadFailed extends HostelManagementException {
	public DocumentUploadFailed(String message) {
		super(message);
	}

	public DocumentUploadFailed(String message, Throwable throwable) {
		super(message, throwable);
	}
}
