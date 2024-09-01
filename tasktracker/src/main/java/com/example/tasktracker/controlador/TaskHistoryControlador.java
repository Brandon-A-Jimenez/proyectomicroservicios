package com.example.tasktracker.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.tasktracker.modelo.TaskHistory;
import com.example.tasktracker.servicio.TaskHistoryService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
public class TaskHistoryControlador {
    @Autowired
    private TaskHistoryService taskHistoryService;
    @GetMapping("/{id}/history")
    public List<TaskHistory> getTaskHistory(@PathVariable Long id) {
        return taskHistoryService.getHistoryByTaskId(id);
    }

}
