package com.example.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.util.Category;
import com.example.util.TaskStatus;

@Data
public class UpdateTaskRequest {
    private String taskId;
    private String taskName;
    private Category category;
    private TaskStatus taskStatus;
    private String taskDescription;
    private LocalDateTime deadLineDate;
}