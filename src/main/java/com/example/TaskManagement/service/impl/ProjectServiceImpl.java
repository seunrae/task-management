package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.dto.*;
import com.example.TaskManagement.models.*;
import com.example.TaskManagement.repository.ProjectRepository;
import com.example.TaskManagement.repository.TaskRepository;
import com.example.TaskManagement.repository.UserRepository;
import com.example.TaskManagement.service.ProjectService;
import com.example.TaskManagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @Override
    public ProjectTaskResponse createProject(ProjectRequest projectRequest, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
        Project project = Project.builder()
                .projectname(projectRequest.getProjectname())
                .description(projectRequest.getDescription())
                .deadline(projectRequest.getDeadline())
                .userProject(user)
                .projectTasks(new ArrayList<>())
                .build();
        projectRepository.save(project);
        return getProjectResponse(project);
    }


    @Override
    public ProjectTaskResponse updateProject(ProjectRequest projectRequest, Integer projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new IllegalStateException("Project nit found"));
        project.setProjectname(projectRequest.getProjectname());
        project.setDescription(projectRequest.getDescription());
        project.setDeadline(projectRequest.getDeadline());
        projectRepository.save(project);
        return getProjectResponse(project);
    }

    @Override
    public String deleteProject(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new IllegalStateException("Project nit found"));
        projectRepository.delete(project);
        return "project deleted";
    }

    @Override
    public ProjectTaskResponse getProjectById(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new IllegalStateException("Project nit found"));
        return getProjectResponse(project);
    }

    @Override
    public List<ProjectTaskResponse> getAllProject() {
        List<Project> projects = projectRepository.findAll();

        return projectTaskResponseList(projects);
    }

//    public List<ProjectResponse> projectResponseList(List<Project> projects) {
//        List<ProjectResponse> projectResponseList = new ArrayList<>();
//        for (Project project: projects) {
//            projectResponseList.add(getProjectResponse(project));
//        }
//        return projectResponseList;
//    }

    public ProjectTaskResponse getProjectResponse(Project project) {
//        ProjectResponse projectResponse = new ProjectResponse();
//
//        projectResponse.setId(project.getId());
//        projectResponse.setProjectname(project.getProjectname());
//        projectResponse.setDescription(project.getDescription());
//        projectResponse.setDeadline(project.getDeadline());
//        projectResponse.setCreatedAt(project.getCreatedAt());
//        projectResponse.setUpdatedAt(project.getUpdatedAt());

        return getProjectTaskResponse(project);
    }

    @Override
    public List<ProjectTaskResponse> searchProject(String projectName) {
        List<Project> projectList = projectRepository.findByNameContaining(projectName);
        return projectTaskResponseList(projectList);
    }

    @Override
    public List<ProjectResponse> projectResponseList(List<Project> projects) {
        return null;
    }

    public List<ProjectTaskResponse> projectTaskResponseList(List<Project> projects) {
        List<ProjectTaskResponse> projectTaskResponseList = new ArrayList<>();
        for (Project project: projects) {
            projectTaskResponseList.add(getProjectTaskResponse(project));
        }
        return projectTaskResponseList;
    }

    public ProjectTaskResponse getProjectTaskResponse(Project project) {
        ProjectTaskResponse projectTaskResponse = new ProjectTaskResponse();

        projectTaskResponse.setId(project.getId());
        projectTaskResponse.setProjectname(project.getProjectname());
        projectTaskResponse.setDescription(project.getDescription());
        projectTaskResponse.setDeadline(project.getDeadline());
        projectTaskResponse.setCreatedAt(project.getCreatedAt());
        projectTaskResponse.setUpdatedAt(project.getUpdatedAt());

        List<TaskProjectResponse> taskProjectResponseList = taskService.taskProjectResponseList(project.getProjectTasks());

        projectTaskResponse.setProjectTasks(taskProjectResponseList);


        return projectTaskResponse;
    }
}
