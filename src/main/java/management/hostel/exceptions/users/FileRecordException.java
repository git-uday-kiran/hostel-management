package management.hostel.exceptions.users;

import management.hostel.exceptions.HostelManagementException;

public class FileRecordException extends HostelManagementException {
	public FileRecordException(String message) {
		super(message);
	}

	public FileRecordException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
