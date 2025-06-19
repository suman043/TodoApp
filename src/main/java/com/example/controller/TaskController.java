package com.example.controller;

import com.example.dto.reponse.GetTaskData;
import com.example.dto.reponse.GetTaskResponse;
import com.example.exception.SumanException;
import com.example.service.TaskService;
import com.example.dto.reponse.TaskResponse;
import com.example.dto.request.CreateTaskRequest;
import com.example.dto.request.UpdateTaskRequest;
import com.example.dto.request.DeleteTaskRequest;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponse createTask(@RequestBody CreateTaskRequest createTaskRequest) throws SumanException {
        return taskService.createTask(createTaskRequest);
    }

    @PatchMapping
    public TaskResponse updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) throws SumanException {
        return taskService.updateTask(updateTaskRequest);
    }

    @DeleteMapping
    public TaskResponse deleteTask(@RequestBody DeleteTaskRequest deleteTaskRequest) throws SumanException{
        return taskService.deleteTask(deleteTaskRequest);
    }

    @GetMapping
    public GetTaskResponse readTaskById(@RequestParam("taskId") String taskId) throws SumanException{
        return taskService.readTask(taskId);
    }

    @GetMapping("/all")
    public List<GetTaskData> readAllTasks() throws SumanException {
        return taskService.readAllTasks();
    }
}