package com.example.TaskManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
@Getter
@Setter
@ToString
public class ProjectRequest {
    @NotBlank
    private String projectname;
    @NotBlank
    private String description;
    @NotNull
    private LocalDate deadline;
}
