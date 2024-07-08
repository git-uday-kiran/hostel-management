package management.hostel.exceptions.identity;

import management.hostel.exceptions.HostelManagementException;

public class CountryException extends HostelManagementException {
	public CountryException(String message) {
		super(message);
	}

	public CountryException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
