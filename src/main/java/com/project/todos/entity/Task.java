package com.project.todos.entity;
import com.project.todos.utils.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
    @SequenceGenerator(name = "task_generator", sequenceName = "sequence_task", allocationSize = 1)
    @Digits(integer = 18, fraction = 0, message = "Id length must not be greater than 18 integral digits ")
    @Column(name="task_id")
    private Long taskId;

    @Size(max = 30, message = "Description must not be greater than 30")
    @NotNull
    @Column(name="name")
    private String name;

    @Size(max = 50, message = "Description must not be greater than 50")
    @Column(name="description")
    private String description;

    @Column(name="last_updated")
    private LocalDateTime lastUpdated;

    @Column(name="status")
    @NotNull
    private Status status;

}