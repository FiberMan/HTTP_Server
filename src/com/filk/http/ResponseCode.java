package com.filk.http;

public enum ResponseCode {
    OK(200, "OK"),
    NOT_FOUND(404, "Not found"),
    SERVER_ERROR(500, "Server error"),
    BAD_REQUEST(400, "Bad request");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseCode getByCode(int code) {
        ResponseCode[] responseCodes = values();
        for (ResponseCode responseCode : responseCodes) {
            if (responseCode.code == code) {
                return responseCode;
            }
        }
        throw new IllegalArgumentException("No method for code: " + code + " found");
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}