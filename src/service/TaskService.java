package service;

import model.Task;
import repository.TaskRepository;
import utils.IdGenerator;

import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String title, String userId) {
        String id = IdGenerator.generateId();

        Task task = new Task(id, title, false, userId);
        taskRepository.save(task);

        return task;
    }

    public Task getTask(String id) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUser(String userId) {
        return taskRepository.findByUserId(userId);
    }

    public boolean deleteTask(String id) {
        return taskRepository.delete(id);
    }

    public void markTaskCompleted(String id) {
        Task task = taskRepository.findById(id);

        if (task != null) {

        }
    }
}
