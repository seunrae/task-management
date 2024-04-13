package com.example.TaskManagement.service;

import com.example.TaskManagement.dto.ProjectRequest;
import com.example.TaskManagement.dto.ProjectResponse;
import com.example.TaskManagement.dto.ProjectTaskResponse;
import com.example.TaskManagement.dto.TaskRequest;

import java.util.List;

public interface ProjectTaskService {
    ProjectTaskResponse addProjectTask(Integer projectId, TaskRequest taskRequest);
    ProjectResponse updateProjectTask(TaskRequest taskRequest, Integer  projectId, Integer taskId);
    String deleteProjectTask(Integer projectId, Integer taskId);

}
