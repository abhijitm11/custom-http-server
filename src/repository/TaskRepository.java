package repository;

import model.Task;
import java.util.List;

public interface TaskRepository {
    void save(Task task);
    Task findById(String id);
    List<Task> findAll();
    List<Task> findByUserId(String userId);
    boolean delete(String id);
}
