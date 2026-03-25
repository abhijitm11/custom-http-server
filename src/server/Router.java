package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {

    private final Map<String, List<Route>> routes = new HashMap<>();

    public void addRoute(String method, String pathPattern, RouteHandler handler) {
        routes.putIfAbsent(method, new ArrayList<>());              // If method not present → create list
        routes.get(method).add(new Route(method, pathPattern, handler));

    }

    public String route(HttpRequest request) {
        String method = request.getMethod();           // eg: "GET"
        List<Route> methodRoutes = routes.get(method);             // methodRoutes = [Route(GET, "/users"),Route(GET, "/users/{id}")]

        if(methodRoutes == null) {
            return buildResponse(404, "Not Found");
        }

        // Match path patterns
        for(Route route: methodRoutes) {
            Map<String, String> pathParams = matchPath(route.getPathPattern(), request.getPath());        // matchPath("/users", "/users/123")

            if(pathParams !=null) {
                request.setPathParams(pathParams);
                try {
                    String body = route.getRouteHandler().handle(request);
                    return buildResponse(200, body);
                } catch (IllegalArgumentException e) {
                    return buildResponse(400, e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                    return buildResponse(500, "Internal Server Error!");
                }
            }
        }
        return buildResponse(404, "Not Found");            // Default: 404
    }

    private Map<String, String> matchPath(String pattern, String actualPath) {
        String[] patternParts = pattern.split("/");
        String[] actualPathParts = actualPath.split("/");

        /*
        patternParts = ["", "users", "{id}"]
        pathParts    = ["", "users", "123"]
        */

        if(patternParts.length != actualPathParts.length) {
            return null;
        }

        Map<String, String> params = new HashMap<>();

        for (int i = 0; i < patternParts.length; i++) {

            String patternPart = patternParts[i];
            String pathPart = actualPathParts[i];

            if (patternPart.startsWith("{") && patternPart.endsWith("}")) {
                String key = patternPart.substring(1, patternPart.length() - 1);
                params.put(key, pathPart);

            } else if (!patternPart.equals(pathPart)) {
                return null;
            }
        }
        return params;

    }

    private String buildResponse(int statusCode, String body) {

        String statusText;
        switch (statusCode) {
            case 201:
                statusText = "Created";
                break;
            case 400:
                statusText = "Bad Request";
                break;
            case 404:
                statusText = "Not Found";
                break;
            case 500:
                statusText = "Internal Server Error";
                break;
            default:
                statusText = "OK";
        }

        return "HTTP/1.1 " + statusCode + " " + statusText + "\r\n" +
                "Content-Type: text/plain\r\n" +
                "Content-Length: " + body.length() + "\r\n" +
                "\r\n" +
                body;
    }
}
