package it.mls.recyclerviewflipper.exception;

/**
 * Created by Angelo Cassano on 04/06/2015.
 */
public class NotAnOddNumberException extends RuntimeException {

    public static final String ERROR = "The number of the items must be an odd number";

    public NotAnOddNumberException() {
        super();
    }

    public NotAnOddNumberException(String detailMessage) {
        super(detailMessage);
    }

    public NotAnOddNumberException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NotAnOddNumberException(Throwable throwable) {
        super(throwable);
    }
}
