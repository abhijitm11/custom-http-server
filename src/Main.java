import model.User;
import server.HttpServer;

public class Main {
    public static void main(String[] args) {
        User user = new User("1", "alex", "alex@test.com");
        System.out.println(user.toString());

        HttpServer server = new HttpServer();
        server.start(8080);
    }
}
