package com.example.service;

import com.example.dto.reponse.GetTaskData;
import com.example.dto.reponse.TaskResponse;
import com.example.dto.reponse.GetTaskResponse;
import com.example.dto.request.CreateTaskRequest;
import com.example.dto.request.DeleteTaskRequest;
import com.example.dto.request.UpdateTaskRequest;
import com.example.exception.SumanException;

import java.util.List;
import java.util.UUID;


public interface TaskService {

    TaskResponse createTask(CreateTaskRequest task) throws SumanException;

    TaskResponse updateTask(UpdateTaskRequest task) throws SumanException;

    TaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest) throws SumanException;

    GetTaskResponse readTask(String taskId) throws SumanException;

    List<GetTaskData> readAllTasks() throws SumanException;
}