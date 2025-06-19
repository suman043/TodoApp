package com.example.dto.reponse;

import lombok.Data;

@Data
public class GetTaskResponse {
    private String message;
    private int statusCode;
    private GetTaskData data;
}