package controller;

import model.User;
import service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String getHello() {
        return "Hello from Controller";
    }

    public String getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.toString();
    }

    public String getUsers(Map<String, String> queryParams) {
        if(queryParams.containsKey("id")) {
            String id = queryParams.get("id");
            User user = userService.getUser(id);

            if(user == null) {
                return "User not found!";
            }
            return user.toString();
        }
        return userService.getAllUsers().toString();   // default: return all users
    }

    public String createUser(String body) {
        Map<String, String> params = parseBody(body);

        String name = params.get("name");
        String email = params.get("email");

        if (name == null || email == null) {
            return "Invalid input";
        }
        userService.createUser(name, email);
        return "User created: " + name;
    }

    // Generic body parser: Example input: "name=abhi&email=test@gmail.com"
    private Map<String, String> parseBody(String body) {
        Map<String, String> parsedBody = new HashMap<>();

        if (body == null || body.trim().isEmpty()) {
            return parsedBody;
        }

        String[] pairs = body.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            String key = keyValue[0].trim();
            String value = keyValue.length > 1 ? keyValue[1].trim() : "";
            parsedBody.put(key, value);
        }
        return parsedBody;
    }
}
