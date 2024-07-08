package management.hostel.exceptions.hostel;

import management.hostel.exceptions.HostelManagementException;

public class AttendanceException extends HostelManagementException {
	public AttendanceException(String message) {
		super(message);
	}

	public AttendanceException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
