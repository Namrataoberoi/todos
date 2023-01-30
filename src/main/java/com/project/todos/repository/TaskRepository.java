package com.project.todos.repository;

import com.project.todos.utils.Mappings;
import com.project.todos.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = Mappings.TASK_COLLECTION_PATH, path = Mappings.TASK_PATH)
public interface TaskRepository extends PagingAndSortingRepository<Task, Long>, CrudRepository<Task,Long> {

    @RestResource
    Task save(Task task);

    @RestResource
    Optional<Task> findByTaskId(Long taskId);

    @Override
    @RestResource
    List<Task> findAll();

    @RestResource
    void deleteByTaskId(Long taskId);

}