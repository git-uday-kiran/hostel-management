package management.hostel.exceptions.users;

import management.hostel.exceptions.HostelManagementException;

public class StudentException extends HostelManagementException {
	public StudentException(String message) {
		super(message);
	}

	public StudentException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
