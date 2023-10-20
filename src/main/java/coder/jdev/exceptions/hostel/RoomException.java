package coder.jdev.exceptions.hostel;

import coder.jdev.exceptions.HostelManagementException;

public class RoomException extends HostelManagementException {
    public RoomException(String message) {
        super(message);
    }

    public RoomException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
