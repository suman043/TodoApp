package com.example.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.UUID;
import java.time.LocalDateTime;

import com.example.util.Category;
import com.example.util.TaskStatus;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue
    private UUID taskId;

    @Column(name = "task_name", nullable = false, updatable = true)
    private String taskName;

    @Column(name = "task_description", length = 1000, updatable = true , nullable = true)
    private String taskDescription;

    @Column(name = "deadline_date", updatable = true, nullable = true)
    private LocalDateTime deadLineDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", updatable = true, nullable = false)
    private Category category;

    @CreationTimestamp
    @Column(name = "date_created" , nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "date_updated" , nullable = false, updatable = true)
    private LocalDateTime dateUpdated;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status", nullable = false , updatable = true)
    private TaskStatus taskStatus;
}