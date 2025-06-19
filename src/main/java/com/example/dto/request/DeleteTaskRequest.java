package com.example.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteTaskRequest {
    private String taskId;
}