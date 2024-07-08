package management.hostel.exceptions.identity;

import management.hostel.exceptions.HostelManagementException;

public class StateException extends HostelManagementException {
	public StateException(String message) {
		super(message);
	}

	public StateException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
