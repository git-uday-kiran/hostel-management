package coder.jdev.exceptions.identity;

import coder.jdev.exceptions.HostelManagementException;

public class CollegeException extends HostelManagementException {
    public CollegeException(String message) {
        super(message);
    }

    public CollegeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
