package com.project.todos.service;

import com.project.todos.entity.Task;
import com.project.todos.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> findByTaskId(Long taskId) {
        return Optional.ofNullable(taskRepository.findByTaskId(taskId)).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteByTaskId(Long taskId) {
        taskRepository.deleteByTaskId(taskId);
    }
}