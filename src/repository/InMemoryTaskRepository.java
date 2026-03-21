package repository;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskRepository implements TaskRepository {

    private final Map<String, Task> tasks = new HashMap<>();

    @Override
    public void save(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public Task findById(String id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Task> findByUserId(String userId) {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks.values()) {
            if (userId.equals(task.getUserId())) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        return tasks.remove(id) != null;
    }
}
