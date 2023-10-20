package coder.jdev.exceptions.users;

import coder.jdev.exceptions.HostelManagementException;

public class StaffException extends HostelManagementException {
    public StaffException(String message) {
        super(message);
    }

    public StaffException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
