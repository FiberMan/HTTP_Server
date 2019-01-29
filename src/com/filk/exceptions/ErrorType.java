package com.filk.exceptions;

public enum ErrorType {

    NOT_FOUND(404, "Not found"),
    BAD_REQUEST(400, "Bad request"),
    INTERNAL_SERVER_ERROR(500, "Internal Server error"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed");

    private final int statusCode;
    private final String statusMessage;


    ErrorType(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public String getStatusLine() {
        return statusCode + " " + statusMessage;
    }
}