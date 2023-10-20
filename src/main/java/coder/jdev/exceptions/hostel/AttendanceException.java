package coder.jdev.exceptions.hostel;

import coder.jdev.exceptions.HostelManagementException;

public class AttendanceException extends HostelManagementException {
    public AttendanceException(String message) {
        super(message);
    }

    public AttendanceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
