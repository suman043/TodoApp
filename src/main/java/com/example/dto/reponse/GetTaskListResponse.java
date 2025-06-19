package com.example.dto.reponse;

import lombok.Data;

import java.util.List;

@Data
public class GetTaskListResponse {
    private String message;
    private int statusCode;
    private List<GetTaskData> data;
}