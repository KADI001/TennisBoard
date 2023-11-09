package org.kadirov.service.exception;

public class MatchAlreadyActiveException extends Exception {
    public MatchAlreadyActiveException() {
    }

    public MatchAlreadyActiveException(String message) {
        super(message);
    }

    public MatchAlreadyActiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatchAlreadyActiveException(Throwable cause) {
        super(cause);
    }

    public MatchAlreadyActiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
