package coder.jdev.exceptions.hostel;

import coder.jdev.exceptions.HostelManagementException;

public class HostelException extends HostelManagementException {
    public HostelException(String message) {
        super(message);
    }

    public HostelException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
