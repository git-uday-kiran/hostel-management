package coder.jdev.exceptions.users;

import coder.jdev.exceptions.HostelManagementException;

public class UserException extends HostelManagementException {
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
