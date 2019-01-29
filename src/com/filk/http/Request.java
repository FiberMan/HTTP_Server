package com.filk.http;

import java.io.InputStream;
import java.util.Map;

public class Request {
    private HttpMethod httpMethod;
    private String httpUri;
    private String httpVersion;
    private Map<String, String> headers;
    private InputStream bodyContentInputStream;

    public Request() {
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getHttpUri() {
        return httpUri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public InputStream getBodyContentInputStream() {
        return bodyContentInputStream;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setHttpUri(String httpUri) {
        this.httpUri = httpUri;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBodyContentInputStream(InputStream inputStream) {
        this.bodyContentInputStream = inputStream;
    }

    @Override
    public String toString() {
        return "Request: " + httpMethod + " " + httpUri;
    }
}