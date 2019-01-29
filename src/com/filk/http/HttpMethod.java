package com.filk.http;

import com.filk.exceptions.ErrorType;
import com.filk.exceptions.ServerException;

public enum HttpMethod {
    GET("get", "read"),
    POST("post", "add"),
    PUT("put", "update"),
    DELETE("delete", "remove");

    private final String name;
    private final String restAction;

    HttpMethod(String name, String restAction) {
        this.name = name;
        this.restAction = restAction;
    }

    public static HttpMethod getByName(String name) {
        HttpMethod[] httpMethods = values();
        for (HttpMethod httpMethod : httpMethods) {
            if (httpMethod.name.equalsIgnoreCase(name)) {
                return httpMethod;
            }
        }
        throw new ServerException(ErrorType.METHOD_NOT_ALLOWED);
    }

    public String getName() {
        return name;
    }

    public String getRestAction() {
        return restAction;
    }


}