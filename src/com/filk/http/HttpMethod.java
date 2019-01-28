package com.filk.http;

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

    public static HttpMethod getByName(String name) throws IllegalArgumentException {
        HttpMethod[] httpMethods = values();
        for (HttpMethod httpMethod : httpMethods) {
            if (httpMethod.name.equalsIgnoreCase(name)) {
                return httpMethod;
            }
        }
        throw new IllegalArgumentException("No method for name: " + name + " found");
    }

    public String getName() {
        return name;
    }

    public String getRestAction() {
        return restAction;
    }


}