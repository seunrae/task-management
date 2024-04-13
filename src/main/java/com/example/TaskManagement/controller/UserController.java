package com.example.TaskManagement.controller;

import com.example.TaskManagement.dto.JwtResponse;
import com.example.TaskManagement.dto.ProjectResponse;
import com.example.TaskManagement.dto.TaskResponse;
import com.example.TaskManagement.dto.UserRequest;
import com.example.TaskManagement.models.User;
import com.example.TaskManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<JwtResponse> getUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<JwtResponse>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PatchMapping("/editUser/{userId}")
    public ResponseEntity<User> editUser(@RequestBody UserRequest userRequest, @PathVariable  Integer userId) {
        return ResponseEntity.ok(userService.updateUser(userRequest, userId));
    }

    @DeleteMapping(value = "/deleteUser/{userId}", produces = "application/json")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @GetMapping("/get-userTask-status/{id}")
    public ResponseEntity<List<TaskResponse>> getUserTaskStatus(@PathVariable Integer id, @RequestParam(name = "status") String status){
        return ResponseEntity.ok(userService.getUserTaskStatus(id, status));
    }

    @GetMapping("/orderUserPriority/{id}")
    public ResponseEntity<List<TaskResponse>> orderUserPriority(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.orderUserPriority(id));
    }

    @GetMapping("/searchUserTask/{id}")
    public ResponseEntity<List<TaskResponse>> searchUserTask(@PathVariable Integer id, @RequestParam(name = "name") String taskName){
        return ResponseEntity.ok(userService.searchUserTask(id, taskName));
    }

    @GetMapping("/searchUserProject/{id}")
    public ResponseEntity<List<ProjectResponse>> searchUserProject(@PathVariable Integer id, @RequestParam(name = "name") String projectName){
        return ResponseEntity.ok(userService.searchUserProject(id, projectName));
    }




}
