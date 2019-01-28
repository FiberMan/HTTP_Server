package com.filk.http;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private final String HTTP_VERSION = "HTTP/1.0";
    private List<String> responseStrings;
    private ResponseCode responseCode;

    public Response(ResponseCode responseCode, List<String> responseBody) {
        this.responseCode = responseCode;
        responseStrings = new ArrayList<>();
        responseStrings.add(HTTP_VERSION + " " + responseCode.getCode() + " " + responseCode.getMessage());
        responseStrings.add("");
        responseStrings.addAll(responseBody);
    }

    public List<String> getResponseStrings() {
        return responseStrings;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
