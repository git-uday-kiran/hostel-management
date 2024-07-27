package management.hostel.exceptions.users;

import management.hostel.exceptions.HostelManagementException;

public class UserException extends HostelManagementException {
	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
