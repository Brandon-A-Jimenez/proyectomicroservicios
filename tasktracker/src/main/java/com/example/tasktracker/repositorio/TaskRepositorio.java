package com.example.tasktracker.repositorio;

import com.example.tasktracker.modelo.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositorio extends JpaRepository<Task, Long> {
}
