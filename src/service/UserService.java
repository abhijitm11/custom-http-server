package service;

import model.User;
import repository.UserRepository;
import utils.IdGenerator;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String name, String email) {
        String id = IdGenerator.generateId();
        User user = new User();
        user.setName(name);
        user.setEmail(email);

        userRepository.save(user);
    }

    public User getUser(String id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUser(String id) {
        return userRepository.delete(id);
    }
}
