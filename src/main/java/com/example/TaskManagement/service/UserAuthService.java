package com.example.TaskManagement.service;

import com.example.TaskManagement.dto.JwtResponse;
import com.example.TaskManagement.dto.LogInRequest;
import com.example.TaskManagement.dto.SignInRequest;
import com.example.TaskManagement.models.User;

public interface UserAuthService {
    User createUser(SignInRequest signInRequest);

    JwtResponse logIn(LogInRequest logInRequest);
}
