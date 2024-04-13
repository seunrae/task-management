package com.example.TaskManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class ProjectTaskResponse {
    private Integer id;

    private String projectname;

    private String description;

    private LocalDate deadline;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private List<TaskProjectResponse> projectTasks;
}
