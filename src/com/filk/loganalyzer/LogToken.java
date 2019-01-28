package com.filk.loganalyzer;

import com.filk.http.HttpMethod;

import java.time.LocalDateTime;

public class LogToken {
    private LocalDateTime time;
    private HttpMethod method;
    private String message;

    public LogToken(LocalDateTime time, HttpMethod method, String message) {
        this.time = time;
        this.method = method;
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LogToken{" +
                "time=" + time +
                ", method=" + method +
                ", message='" + message + '\'' +
                '}';
    }
}
