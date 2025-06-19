package com.example.dto.reponse;

import lombok.Data;

import java.util.UUID;
import java.time.LocalDateTime;

import com.example.util.Category;
import com.example.util.TaskStatus;

@Data
public class GetTaskData {
    private UUID taskId;
    private String taskName;
    private Category category;
    private TaskStatus taskStatus;
    private String taskDescription;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private LocalDateTime deadLineDate;
}