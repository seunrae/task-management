package com.example.TaskManagement.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {
    @NotBlank
    @Min(3)
    @Max(10)
    private String firstname;
    @NotBlank
    @Min(3)
    @Max(10)
    private String lastname;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Min(5)
    @Max(256)
    private String password;
}
