package com.project.todos.controller;

import com.project.todos.service.TaskService;
import com.project.todos.entity.Task;
import com.project.todos.utils.Mappings;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;


@Controller
@RequestMapping(Mappings.TASK_COLLECTION_PATH)
public class TaskController {

    private final TaskService taskService;

    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/" + Mappings.TASK_PATH + "/" + Mappings.ADD)
    public String add(Model model) {
        Task taskToCreate = new Task();
        model.addAttribute("taskToCreate", taskToCreate);
        return "createTask";
    }

    @PostMapping("/" + Mappings.TASK_PATH + "/" + Mappings.CREATE)
    public String create(@ModelAttribute("taskToCreate") Task task, Model model) {
        task.setLastUpdated(LocalDateTime.now());
        taskService.save(task);
        model.addAttribute("tasks", taskService.findAll());
        return "taskList";
    }

    @GetMapping("/" + Mappings.TASK_PATH + "/" + Mappings.EDIT)
    public String edit(@Valid @RequestParam Long id, Model model) {
        Task taskToEdit = taskService.findByTaskId(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        model.addAttribute("taskToEdit", taskToEdit);
        return "editTask";
    }

    @PostMapping("/" + Mappings.TASK_PATH + "/" + Mappings.UPDATE)
    public String update(@ModelAttribute("taskToEdit") Task task, Model model) {
        taskService.findByTaskId(task.getTaskId()).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setLastUpdated(LocalDateTime.now());
        taskService.save(task);
        model.addAttribute("tasks", taskService.findAll());
        return "taskList";
    }

    @GetMapping("/" + Mappings.TASK_PATH)
    public String findAll(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "taskList";
    }

    @PostMapping("/" + Mappings.TASK_PATH + "/" + Mappings.DELETE)
    public String delete(@Valid @RequestParam Long id, Model model) {
        taskService.findByTaskId(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskService.deleteByTaskId(id);
        model.addAttribute("tasks", taskService.findAll());
        return "taskList";
    }

}