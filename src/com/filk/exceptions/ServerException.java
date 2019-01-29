package com.filk.exceptions;

public class ServerException extends RuntimeException {
    private ErrorType errorType;

    public ServerException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
