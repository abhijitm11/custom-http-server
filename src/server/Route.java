package server;

public class Route {
    private final String method;
    private final String pathPattern;
    private final RouteHandler routeHandler;

    public Route(String method, String pathPattern, RouteHandler routeHandler) {
        this.method = method;
        this.pathPattern = pathPattern;
        this.routeHandler = routeHandler;
    }

    public String getMethod() {
        return method;
    }

    public String getPathPattern() {
        return pathPattern;
    }

    public RouteHandler getRouteHandler() {
        return routeHandler;
    }
}
