package com.project.todos.service;

import com.project.todos.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task save(Task task);
    Optional<Task> findByTaskId(Long taskId);
    List<Task> findAll();
    void deleteByTaskId(Long taskId);

}
