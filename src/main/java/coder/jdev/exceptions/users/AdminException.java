package coder.jdev.exceptions.users;

import coder.jdev.exceptions.HostelManagementException;

public class AdminException extends HostelManagementException {
    public AdminException(String message) {
        super(message);
    }

    public AdminException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
