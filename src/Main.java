import controller.UserController;
import model.User;
import repository.InMemoryUserRepository;
import repository.UserRepository;
import server.HttpServer;
import server.Router;
import service.UserService;

public class Main {
    public static void main(String[] args) {


        // 🔹 Repository
        UserRepository userRepo = new InMemoryUserRepository();

        // 🔹 Service
        UserService userService = new UserService(userRepo);

        // 🔹 Controller
        UserController userController = new UserController(userService);


        userService.createUser("Hailey", "hailey@test.com");
        userService.createUser("Claire", "claire@test.com");


        Router router = new Router(userController);

        HttpServer server = new HttpServer(router);
        server.start(8080);
    }
}
