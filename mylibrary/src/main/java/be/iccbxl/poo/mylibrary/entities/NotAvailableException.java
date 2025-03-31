package be.iccbxl.poo.mylibrary.entities;

public class NotAvailableException extends RuntimeException {
    public NotAvailableException() {
        super();
    }

    public NotAvailableException(String message) {
        super(message);
    }
}
