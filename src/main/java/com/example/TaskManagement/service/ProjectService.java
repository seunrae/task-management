package com.example.TaskManagement.service;

import com.example.TaskManagement.dto.*;
import com.example.TaskManagement.models.Project;

import java.util.List;

public interface ProjectService {
    ProjectTaskResponse createProject(ProjectRequest projectRequest, Integer userId);
    ProjectTaskResponse updateProject(ProjectRequest projectRequest, Integer projectId);
    String deleteProject(Integer projectId);
    ProjectTaskResponse getProjectById(Integer projectId);
    List<ProjectTaskResponse> getAllProject();

    ProjectTaskResponse getProjectResponse(Project project);

    List<ProjectTaskResponse> searchProject (String projectName);
    List<ProjectResponse> projectResponseList(List<Project> projects);



}
