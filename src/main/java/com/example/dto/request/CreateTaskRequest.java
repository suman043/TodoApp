package com.example.dto.request;

import lombok.Data;

import com.example.util.Category;
import com.example.util.TaskStatus;

import java.time.LocalDateTime;

@Data
public class CreateTaskRequest {
    private String taskName;
    private Category category;
    private TaskStatus taskStatus;
    private String taskDescription;
    private LocalDateTime deadLineDate;
}