package com.project.todos.service;

import com.project.todos.entity.Task;
import com.project.todos.repository.TaskRepository;
import com.project.todos.utils.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskServiceImpl service;

    @Mock
    private TaskRepository repository;

    @Test
    public void saveTest() {
        Task task = Task.builder().taskId(1L).name("task1").description("task 1 description").status(Status.PENDING).build();

        when(repository.save(any(Task.class))).thenReturn(task);

        final Task expectedTask = service.save(task);

        assertThat(expectedTask).usingRecursiveComparison().isEqualTo(task);
        verify(repository, times(1)).save(any(Task.class));
    }

    @Test
    public void findByTaskIdTest() {
        Optional<Task> task = Optional.ofNullable(Task.builder().taskId(1L).name("task1").description("task 1 description").status(Status.PENDING).build());

        when(repository.findByTaskId(anyLong())).thenReturn(task);

        final Optional<Task> expectedTask = service.findByTaskId(1L);

        assertThat(expectedTask).usingRecursiveComparison().isEqualTo(task);
        verify(repository, times(1)).findByTaskId(anyLong());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByTaskIdTest_Failure_NotFound() {
        when(repository.findByTaskId(anyLong())).thenReturn(null);
        service.findByTaskId(1L);
    }

    @Test
    public void findAllTest() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(Task.builder().taskId(1L).name("task1").description("task 1 description").status(Status.PENDING).build());

        when(repository.findAll()).thenReturn(tasks);

        final List<Task> expectedTasks = service.findAll();

        assertThat(expectedTasks).usingRecursiveComparison().isEqualTo(tasks);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void deleteByTaskIdTest() {
        doNothing().when(repository).deleteByTaskId(anyLong());
        service.deleteByTaskId(anyLong());
        verify(repository, times(1)).deleteByTaskId(anyLong());
    }

}