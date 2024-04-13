package com.example.TaskManagement.controller;

import com.example.TaskManagement.dto.TaskRequest;
import com.example.TaskManagement.dto.TaskResponse;
import com.example.TaskManagement.models.Task;
import com.example.TaskManagement.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/createTask/{userId}")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest, @PathVariable Integer userId) {
        return ResponseEntity.ok(taskService.createTask(taskRequest, userId));
    }
    @GetMapping("/allTask")
    public ResponseEntity<List<TaskResponse>> getTasks() {
        return ResponseEntity.ok(taskService.getAllTask());
    }
    @PatchMapping("/updateTask/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@RequestBody TaskRequest taskRequest, @PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.updateTask(taskRequest, taskId));
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<String> deleteTask (@PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.deleteTask(taskId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }
    @GetMapping("/status")
    public ResponseEntity<List<TaskResponse>> getTaskByStatus(@RequestParam(name = "status") String status) {
        return ResponseEntity.ok(taskService.getTaskByStatus(status));
    }

    @GetMapping("/order")
    public ResponseEntity<List<TaskResponse>> orderTaskByPriority() {
        return ResponseEntity.ok(taskService.orderTaskByPriority());
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchTask(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(taskService.searchTask(name));
    }

}
