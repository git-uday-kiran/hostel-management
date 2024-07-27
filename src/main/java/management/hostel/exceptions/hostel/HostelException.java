package management.hostel.exceptions.hostel;

import management.hostel.exceptions.HostelManagementException;

public class HostelException extends HostelManagementException {
	public HostelException(String message) {
		super(message);
	}

	public HostelException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
