package com.example.TaskManagement.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TaskProjectResponse {
    private Integer id;

    private String taskname;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate deadline;

    private String priority;

    private String status;
}
