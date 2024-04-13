package com.example.TaskManagement.service;

import com.example.TaskManagement.dto.TaskProjectResponse;
import com.example.TaskManagement.dto.TaskRequest;
import com.example.TaskManagement.dto.TaskResponse;
import com.example.TaskManagement.models.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest, Integer id);
    TaskResponse updateTask(TaskRequest taskRequest, Integer taskId);
    String deleteTask(Integer id);

    TaskResponse getTaskById(Integer id);
    List<TaskResponse> getAllTask();

    List<TaskResponse> getTaskByStatus(String status);

    List<TaskResponse> orderTaskByPriority();
    List<TaskResponse> taskResponseList(List<Task> tasks);
    List<TaskProjectResponse> taskProjectResponseList(List<Task> tasks);

    List<TaskResponse> searchTask(String name);

}
