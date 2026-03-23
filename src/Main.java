import controller.UserController;
import model.User;
import repository.InMemoryUserRepository;
import repository.UserRepository;
import server.HttpServer;
import server.Router;
import service.UserService;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepo = new InMemoryUserRepository();
        UserService userService = new UserService(userRepo);
        UserController userController = new UserController(userService);

        Router router = new Router();

        router.addRoute("GET", "/users", (req) ->{
            return userController.getAllUsers();
        });

        router.addRoute("GET", "/users/{id}", (req) -> {
            String id = req.getPathParams().get("id");
            return userController.getUserById(id);
        });

        router.addRoute("POST", "/users", (req) -> {
            return userController.createUser(req.getBody());
        });

        HttpServer server = new HttpServer(router);
        server.start(8080);

    }
}
