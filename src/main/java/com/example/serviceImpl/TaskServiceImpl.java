package com.example.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import com.example.model.Task;
import com.example.service.TaskService;
import com.example.dto.reponse.GetTaskData;
import com.example.dto.reponse.TaskResponse;
import com.example.exception.SumanException;
import com.example.repository.TaskRepository;
import com.example.exception.ExceptionHandler;
import com.example.dto.reponse.GetTaskResponse;
import com.example.dto.request.DeleteTaskRequest;
import com.example.dto.request.UpdateTaskRequest;
import com.example.dto.request.CreateTaskRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ExceptionHandler exceptionHandler;

    public TaskServiceImpl(TaskRepository taskRepository, ExceptionHandler exceptionHandler) {
        this.taskRepository = taskRepository;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest createTaskRequest) throws SumanException {
        try {
            validateCreateTaskRequest(createTaskRequest);
            Task task = new Task();
            task.setTaskName(createTaskRequest.getTaskName());
            task.setCategory(createTaskRequest.getCategory());
            task.setTaskStatus(createTaskRequest.getTaskStatus());
            task.setDeadLineDate(createTaskRequest.getDeadLineDate());
            task.setTaskDescription(createTaskRequest.getTaskDescription());

            taskRepository.save(task);
        } catch (Exception e) {
            exceptionHandler.handleException(e);
            throw new SumanException("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setStatusCode(200);
        taskResponse.setMessage("Task Created Successfully");
        return taskResponse;
    }

    @Override
    public TaskResponse updateTask(UpdateTaskRequest updateTaskRequest) throws SumanException {
        try {
            String taskId = updateTaskRequest.getTaskId();
            validateTaskId(taskId);
            Task taskById = taskRepository.getTaskByTaskId(UUID.fromString(taskId));

            if (taskById == null) {
                throw new SumanException("TaskID Not Found", HttpStatus.BAD_REQUEST);
            }
            if (!updateTaskRequest.getTaskName().trim().isEmpty()) {
                taskById.setTaskName(updateTaskRequest.getTaskName());
            }
            if (updateTaskRequest.getCategory() != null) {
                taskById.setCategory(updateTaskRequest.getCategory());
            }
            if (updateTaskRequest.getTaskStatus() != null) {
                taskById.setTaskStatus(updateTaskRequest.getTaskStatus());
            }
            if (updateTaskRequest.getDeadLineDate() != null) {
                taskById.setDeadLineDate(updateTaskRequest.getDeadLineDate());
            }
            if (!updateTaskRequest.getTaskDescription().trim().isEmpty()) {
                taskById.setTaskDescription(updateTaskRequest.getTaskDescription());
            }
            if (updateTaskRequest.getDateUpdated() != null) {
                taskById.setDateUpdated(updateTaskRequest.getDateUpdated());
            }
            taskRepository.save(taskById);

        } catch (Exception e) {
            exceptionHandler.handleException(e);
            throw new SumanException("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setStatusCode(200);
        taskResponse.setMessage("Task Updated Successfully");
        return taskResponse;
    }

    @Override
    public TaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest) throws SumanException {
        try {
            String taskId = deleteTaskRequest.getTaskId();
            validateTaskId(taskId);
            Task taskById = taskRepository.getTaskByTaskId(UUID.fromString(taskId));
            if (taskById == null) {
                throw new SumanException("Task not found", HttpStatus.BAD_REQUEST);
            }
            taskRepository.delete(taskById);
        } catch (Exception e) {
            exceptionHandler.handleException(e);
            throw new SumanException("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setStatusCode(200);
        taskResponse.setMessage("Deleted Successfully");
        return taskResponse;
    }

    @Override
    public GetTaskResponse readTask(String taskId) throws SumanException {
        validateTaskId(taskId);
        GetTaskData data = new GetTaskData();
        try {
            Task taskById = taskRepository.getTaskByTaskId(UUID.fromString(taskId));
            if (taskById == null) {
                throw new SumanException("TaskID not found", HttpStatus.BAD_REQUEST);
            }
            data.setTaskId(taskById.getTaskId());
            data.setTaskName(taskById.getTaskName());
            data.setCategory(taskById.getCategory());
            data.setTaskStatus(taskById.getTaskStatus());
            data.setDateCreated(taskById.getDateCreated());
            data.setDateUpdated(taskById.getDateUpdated());
            data.setDeadLineDate(taskById.getDeadLineDate());
            data.setTaskDescription(taskById.getTaskDescription());

        } catch (Exception e) {
            exceptionHandler.handleException(e);
            throw new SumanException("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
        GetTaskResponse getTaskResponse = new GetTaskResponse();
        getTaskResponse.setData(data);
        getTaskResponse.setStatusCode(200);
        getTaskResponse.setMessage("Task fetched successfully");
        return getTaskResponse;
    }

    @Override
    public List<GetTaskData> readAllTasks() throws SumanException {
        List<GetTaskData> data = new ArrayList<>();
        try {
            List<Task> taskList = taskRepository.findAll();
            for (Task task : taskList) {
                GetTaskData getTaskData = new GetTaskData();
                getTaskData.setTaskId(task.getTaskId());
                getTaskData.setCategory(task.getCategory());
                getTaskData.setTaskName(task.getTaskName());
                getTaskData.setTaskStatus(task.getTaskStatus());
                getTaskData.setDateUpdated(task.getDateUpdated());
                getTaskData.setDateCreated(task.getDateCreated());
                getTaskData.setDeadLineDate(task.getDeadLineDate());
                getTaskData.setTaskDescription(task.getTaskDescription());
                data.add(getTaskData);
            }
        } catch (Exception e) {
            exceptionHandler.handleException(e);
            throw new SumanException("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
        return data;
    }

    private void validateCreateTaskRequest(CreateTaskRequest createTaskRequest) throws SumanException {
        if (createTaskRequest.getCategory() == null) {
            throw new SumanException("Category is required", HttpStatus.BAD_REQUEST);
        }
        if (createTaskRequest.getTaskStatus() == null) {
            throw new SumanException("Task status is required", HttpStatus.BAD_REQUEST);
        }
        if (createTaskRequest.getDeadLineDate() == null) {
            throw new SumanException("Deadline date is required", HttpStatus.BAD_REQUEST);
        }
        if (createTaskRequest.getTaskName() == null || createTaskRequest.getTaskName().trim().isEmpty()) {
            throw new SumanException("Task name is required", HttpStatus.BAD_REQUEST);
        }
        if (createTaskRequest.getTaskDescription() == null || createTaskRequest.getTaskDescription().trim().isEmpty()) {
            throw new SumanException("Task description is required", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateTaskId(String taskId) throws SumanException {
        if (taskId == null || taskId.trim().isEmpty()) {
            throw new SumanException("Task Id is required", HttpStatus.BAD_REQUEST);
        }
    }
}