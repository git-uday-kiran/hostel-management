package management.hostel.exceptions.users;

import management.hostel.exceptions.HostelManagementException;

public class AdminException extends HostelManagementException {
	public AdminException(String message) {
		super(message);
	}

	public AdminException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
