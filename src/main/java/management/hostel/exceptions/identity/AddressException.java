package management.hostel.exceptions.identity;

import management.hostel.exceptions.HostelManagementException;

public class AddressException extends HostelManagementException {
	public AddressException(String message) {
		super(message);
	}

	public AddressException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
