package repository;

import model.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    User findById(String id);
    List<User> findAll();
    boolean delete(String id);
}
