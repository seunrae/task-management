package com.example.TaskManagement.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class JwtResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String token;
    private LocalDate createdAt;
    private List<TaskResponse> tasks;
    private List<ProjectTaskResponse> projects;
}
