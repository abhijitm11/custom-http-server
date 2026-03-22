package server;

import controller.UserController;

public class Router {
    private final UserController userController;

    public Router(UserController userController) {
        this.userController = userController;
    }

    public String route(HttpRequest request) {
        String method = request.getMethod();
        String path = request.getPath();

        if(method.equals("GET") && path.equals("/users")) {
            String body = userController.getUsers(request.getQueryParams());
            return buildResponse(200, body);
        }

        if (method.equals("POST") && path.equals("/users")) {
            String body = userController.createUser(request.getBody());
            return buildResponse(201, body);
        }

        return buildResponse(404, "Not Found");            // Default: 404
    }

    private String buildResponse(int statusCode, String body) {

        String statusText;
        if (statusCode == 200) {
            statusText = "OK";
        } else if (statusCode == 201) {
            statusText = "Created";
        } else {
            statusText = "Not Found";
        }

        return "HTTP/1.1 " + statusCode + " " + statusText + "\r\n" +
                "Content-Type: text/plain\r\n" +
                "Content-Length: " + body.length() + "\r\n" +
                "\r\n" +
                body;
    }
}
