package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.dto.*;
import com.example.TaskManagement.models.PRIORITY;
import com.example.TaskManagement.models.Project;
import com.example.TaskManagement.models.STATUS;
import com.example.TaskManagement.models.Task;
import com.example.TaskManagement.repository.ProjectRepository;
import com.example.TaskManagement.repository.TaskRepository;
import com.example.TaskManagement.repository.UserRepository;
import com.example.TaskManagement.service.ProjectTaskService;
import com.example.TaskManagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectTaskServiceImpl implements ProjectTaskService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskService taskService;
    private final TaskRepository taskRepository;
    @Override
    public ProjectTaskResponse addProjectTask(Integer projectId, TaskRequest taskRequest) {
        try {
            Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalStateException("Project not found"));
            List<Project> projectList = projectRepository.findAllById(Collections.singleton(projectId));
            Task task = Task.builder()
                    .taskname(taskRequest.getTaskname())
                    .priority(PRIORITY.valueOf(taskRequest.getPriority().toUpperCase()))
                    .deadline(taskRequest.getDeadline())
                    .user(project.getUserProject())
                    .status(STATUS.valueOf(taskRequest.getStatus().toUpperCase()))
                    .taskProjects(projectList)
                    .build();

//            List<Task> tasks = project.getProjectTasks();
//            tasks.add(task);
//            project.setProjectTasks(tasks);

            project.getProjectTasks().add(task);

            projectRepository.save(project);

            return getProjectTaskResponse(project);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ProjectResponse updateProjectTask(TaskRequest taskRequest, Integer  projectId, Integer taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new IllegalStateException("Task not found"));
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new IllegalStateException("project not found"));
        task.setTaskname(taskRequest.getTaskname());
        task.setDeadline(taskRequest.getDeadline());
        task.setPriority(PRIORITY.valueOf(taskRequest.getPriority().toUpperCase()));
        task.setStatus(STATUS.valueOf(taskRequest.getStatus().toUpperCase()));

        taskRepository.save(task);

        return getProjectResponse(project);
    }

    @Override
    public String deleteProjectTask(Integer projectId, Integer taskId) {
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new IllegalStateException("project not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new IllegalStateException("Task not found"));
        project.getProjectTasks().remove(task);
        return taskService.deleteTask(taskId);
    }

    public List<ProjectResponse> projectResponseList(List<Project> projects) {
        List<ProjectResponse> projectResponseList = new ArrayList<>();
        for (Project project: projects) {
            projectResponseList.add(getProjectResponse(project));
        }
        return projectResponseList;
    }

    public ProjectResponse getProjectResponse(Project project) {
        ProjectResponse projectResponse = new ProjectResponse();

        projectResponse.setId(project.getId());
        projectResponse.setProjectname(project.getProjectname());
        projectResponse.setDescription(project.getDescription());
        projectResponse.setDeadline(project.getDeadline());
        projectResponse.setCreatedAt(project.getCreatedAt());
        projectResponse.setUpdatedAt(project.getUpdatedAt());

        return projectResponse;
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
