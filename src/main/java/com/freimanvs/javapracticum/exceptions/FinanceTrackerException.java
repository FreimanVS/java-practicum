package com.freimanvs.javapracticum.exceptions;

public class FinanceTrackerException extends RuntimeException {

    public FinanceTrackerException() {
    }

    public FinanceTrackerException(String message) {
        super(message);
    }

    public FinanceTrackerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FinanceTrackerException(Throwable cause) {
        super(cause);
    }

    public FinanceTrackerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
