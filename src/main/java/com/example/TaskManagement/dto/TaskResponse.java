package com.example.TaskManagement.dto;

import com.example.TaskManagement.models.PRIORITY;
import com.example.TaskManagement.models.Project;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
public class TaskResponse {
    private Integer id;

    private String taskname;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate deadline;

    private String priority;

    private String  status;

    private List<ProjectResponse> taskProjects;

}
