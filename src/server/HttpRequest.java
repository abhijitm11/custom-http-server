package server;

import java.util.Map;

public class HttpRequest {
    // Think of this class as DTO

    private String method;
    private String path;
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private Map<String, String> pathParams;
    private String body;

    public HttpRequest(String method, String path, Map<String, String> headers, Map<String, String> queryParams, Map<String, String> pathParams, String body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.queryParams = queryParams;
        this.pathParams = pathParams;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public void setPathParams(Map<String, String> pathParams) {
        this.pathParams = pathParams;
    }
}
