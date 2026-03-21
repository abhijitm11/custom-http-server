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

    public User createUser(String name, String email) {
        String id = IdGenerator.generateId();

        User user = new User(id, name, email);
        userRepository.save(user);
        return user;
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
