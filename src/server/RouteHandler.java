package server;

public interface RouteHandler {
    String handle(HttpRequest httpRequest);
}
