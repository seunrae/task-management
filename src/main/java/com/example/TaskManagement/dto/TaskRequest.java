package com.example.TaskManagement.dto;

import com.example.TaskManagement.models.PRIORITY;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class TaskRequest {
    @NotBlank
    private String taskname;
    @NotNull
    private LocalDate deadline;
    @NotBlank
    private String priority;
    @NotBlank
    private String status;

}
