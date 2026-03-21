import model.User;

public class Main {
    public static void main(String[] args) {
        User user = new User("1", "alex", "alex@test.com");
        System.out.println(user.toString());
    }
}
