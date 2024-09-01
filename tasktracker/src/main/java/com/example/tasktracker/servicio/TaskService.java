package com.example.tasktracker.servicio;

import com.example.tasktracker.modelo.Task;
import com.example.tasktracker.repositorio.TaskRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepositorio taskRepository;

    @Autowired
    private TaskHistoryService taskHistoryService;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        task.setCreatedDate(LocalDateTime.now());
        Task createdTask = taskRepository.save(task);
        taskHistoryService.saveTaskHistory(createdTask, "Created");
        return createdTask;
    }

    public void deleteTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            taskHistoryService.saveTaskHistory(taskOptional.get(), "Deleted");
            taskRepository.deleteById(id);
        }
    }

    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setDueDate(taskDetails.getDueDate());
            task.setCreatedBy(taskDetails.getCreatedBy());
            Task updatedTask = taskRepository.save(task);
            taskHistoryService.saveTaskHistory(updatedTask, "Updated");
            return updatedTask;
        }
        return null;
    }
}

