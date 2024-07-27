package management.hostel.exceptions.identity;

import management.hostel.exceptions.HostelManagementException;

public class CityException extends HostelManagementException {
	public CityException(String message) {
		super(message);
	}

	public CityException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
