package coder.jdev.exceptions.identity;

import coder.jdev.exceptions.HostelManagementException;

public class AddressException extends HostelManagementException {
    public AddressException(String message) {
        super(message);
    }

    public AddressException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
