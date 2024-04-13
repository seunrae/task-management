package com.example.TaskManagement.service.impl;

import com.example.TaskManagement.dto.ProjectResponse;
import com.example.TaskManagement.dto.TaskProjectResponse;
import com.example.TaskManagement.dto.TaskRequest;
import com.example.TaskManagement.dto.TaskResponse;
import com.example.TaskManagement.models.*;
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
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    @Override
    public TaskResponse createTask(TaskRequest taskRequest, Integer id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found"));
            Task task = Task.builder()
                    .taskname(taskRequest.getTaskname())
                    .priority(PRIORITY.valueOf(taskRequest.getPriority().toUpperCase()))
                    .deadline(taskRequest.getDeadline())
                    .status(STATUS.valueOf(taskRequest.getStatus().toUpperCase()))
                    .user(user)
                    .taskProjects(new ArrayList<>())
                    .build();
            taskRepository.save(task);

           return getTaskResponse(task);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TaskResponse updateTask(TaskRequest taskRequest,  Integer taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new IllegalStateException("Task not found"));
        task.setTaskname(taskRequest.getTaskname());
        task.setDeadline(taskRequest.getDeadline());
        task.setPriority(PRIORITY.valueOf(taskRequest.getPriority().toUpperCase()));
        task.setStatus(STATUS.valueOf(taskRequest.getStatus().toUpperCase()));
        taskRepository.save(task);
        return getTaskResponse(task);
    }

    @Override
    public String deleteTask(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new IllegalStateException("Task not found"));
        taskRepository.delete(task);
        return "Task Successfully deleted";
    }

    @Override
    public TaskResponse getTaskById(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new IllegalStateException("Task not found"));
        return getTaskResponse(task);
    }

    @Override
    public List<TaskResponse> getAllTask() {
        List<Task> tasks = taskRepository.findAll();
        return taskResponseList(tasks);
    }

    @Override
    public List<TaskResponse> getTaskByStatus(String status) {
        List<Task> task = taskRepository.findAllByStatus(STATUS.valueOf(status)).orElseThrow(()-> new IllegalStateException("status not found"));
        return taskResponseList(task);
    }

    @Override
    public List<TaskResponse> orderTaskByPriority() {
        List<Task> tasks = taskRepository.findByOrderByPriorityDesc();
        return taskResponseList(tasks);
    }
//=============================================================================
    public List<TaskResponse> taskResponseList(List<Task> tasks) {
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task: tasks) {
            taskResponseList.add(getTaskResponse(task));
        }
        return taskResponseList;
    }

    public TaskResponse getTaskResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId(task.getId());
        taskResponse.setTaskname(task.getTaskname());
        taskResponse.setPriority(task.getPriority().name());
        taskResponse.setDeadline(task.getDeadline());
        taskResponse.setStatus(task.getStatus().name());
        taskResponse.setCreatedAt(task.getCreatedAt());
        taskResponse.setUpdatedAt(task.getUpdatedAt());
        taskResponse.setTaskProjects(projectResponseList(task.getTaskProjects()));

        return taskResponse;
    }

    public List<TaskProjectResponse> taskProjectResponseList(List<Task> tasks) {
        List<TaskProjectResponse> taskProjectResponseList = new ArrayList<>();
        for (Task task: tasks) {
            taskProjectResponseList.add(getTaskProjectResponse(task));
        }
        return taskProjectResponseList;
    }

    @Override
    public List<TaskResponse> searchTask(String name) {
        List<Task> taskList = taskRepository.finByNameContaining(name);
        return taskResponseList(taskList);
    }

    public TaskProjectResponse getTaskProjectResponse(Task task) {
        TaskProjectResponse taskProjectResponse = new TaskProjectResponse();

        taskProjectResponse.setId(task.getId());
        taskProjectResponse.setTaskname(task.getTaskname());
        taskProjectResponse.setPriority(task.getPriority().name());
        taskProjectResponse.setDeadline(task.getDeadline());
        taskProjectResponse.setStatus(task.getStatus().name());
        taskProjectResponse.setCreatedAt(task.getCreatedAt());
        taskProjectResponse.setUpdatedAt(task.getUpdatedAt());

        return taskProjectResponse;
    }
//====================
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

}
