package com.example.tasktracker.servicio;

import com.example.tasktracker.modelo.Task;
import com.example.tasktracker.modelo.TaskHistory;
import com.example.tasktracker.repositorio.TaskHistoryRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskHistoryService {

    @Autowired
    private TaskHistoryRepositorio taskHistoryRepository;

    public TaskHistory saveTaskHistory(Task task, String action) {
        TaskHistory taskHistory = new TaskHistory();
        taskHistory.setTask(task);
        taskHistory.setAction(action);
        taskHistory.setTimestamp(LocalDateTime.now());
        return taskHistoryRepository.save(taskHistory);
    }
    public List<TaskHistory> getHistoryByTaskId(Long taskId) {
        return taskHistoryRepository.findByTaskId(taskId);
    }
}
