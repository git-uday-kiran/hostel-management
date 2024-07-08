package management.hostel.exceptions.users;

import management.hostel.exceptions.HostelManagementException;

public class StaffException extends HostelManagementException {
	public StaffException(String message) {
		super(message);
	}

	public StaffException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
