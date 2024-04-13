package com.example.TaskManagement.service;

import com.example.TaskManagement.dto.*;
import com.example.TaskManagement.models.User;

import java.util.List;

public interface UserService {
    JwtResponse getUserById(Integer id);
    List<JwtResponse> getAllUsers();
    User updateUser(UserRequest userRequest, Integer id);
    String deleteUser(Integer id);
    List<TaskResponse> getUserTaskStatus(Integer id, String status);
    List<TaskResponse> orderUserPriority(Integer id);
    List<TaskResponse> searchUserTask(Integer id, String taskName);

    List<ProjectResponse> searchUserProject(Integer id, String projectName);


}
