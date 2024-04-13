package com.example.TaskManagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class LogInRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Min(5)
    @Max(256)
    private String password;
}
