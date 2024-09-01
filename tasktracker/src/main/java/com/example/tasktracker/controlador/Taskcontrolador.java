package com.example.tasktracker.controlador;

import com.example.tasktracker.modelo.Task;
import com.example.tasktracker.modelo.TaskHistory;
import com.example.tasktracker.servicio.TaskHistoryService;
import com.example.tasktracker.servicio.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class Taskcontrolador {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskHistoryService taskHistoryService;

    @GetMapping("/{id}/history")
    public List<TaskHistory> getTaskHistory(@PathVariable Long id) {
        return taskHistoryService.getHistoryByTaskId(id);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        Task savedTask = taskService.createTask(task);

        // Registrar en el historial que la tarea fue creada
        taskHistoryService.saveTaskHistory(savedTask, "Created");

        return savedTask;
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

        // Registrar en el historial que la tarea fue eliminada
        Task task = new Task();
        task.setId(id);
        taskHistoryService.saveTaskHistory(task, "Deleted");
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);

        // Registrar en el historial que la tarea fue actualizada
        taskHistoryService.saveTaskHistory(updatedTask, "Updated");

        return updatedTask;
    }
}
