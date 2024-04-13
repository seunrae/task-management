package com.example.TaskManagement.controller;

import com.example.TaskManagement.dto.ProjectRequest;
import com.example.TaskManagement.dto.ProjectResponse;
import com.example.TaskManagement.dto.ProjectTaskResponse;
import com.example.TaskManagement.dto.TaskRequest;
import com.example.TaskManagement.service.ProjectService;
import com.example.TaskManagement.service.ProjectTaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectTaskService projectTaskService;
    @PostMapping("/createProject/{userId}")
    public ResponseEntity<ProjectTaskResponse> createProject(@Valid @RequestBody ProjectRequest projectRequest, @PathVariable Integer userId) {
        return ResponseEntity.ok(projectService.createProject(projectRequest, userId));
    }
    @PatchMapping("/updateProject/{projectId}")
    public ResponseEntity<ProjectTaskResponse> updateProject(@RequestBody ProjectRequest projectRequest, @PathVariable Integer projectId) {
        return ResponseEntity.ok(projectService.updateProject(projectRequest, projectId));
    }
    @DeleteMapping("/deleteProject/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) {
        return ResponseEntity.ok(projectService.deleteProject(projectId));
    }
    @GetMapping("/getProject/{projectId}")
    public ResponseEntity<ProjectTaskResponse> getProjectById(@PathVariable Integer projectId) {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }
    @GetMapping("/allProjects")
    public  ResponseEntity<List<ProjectTaskResponse>> getAllProject() {
        return  ResponseEntity.ok(projectService.getAllProject());
    }

    @PatchMapping("/addProjectTask/{projectId}")
    public ResponseEntity<ProjectTaskResponse> addProjectTask (@Valid @RequestBody TaskRequest taskRequest, @PathVariable Integer projectId) {
        return ResponseEntity.ok(projectTaskService.addProjectTask(projectId, taskRequest));
    }

    @PatchMapping("/updateProjectTask/{projectId}/{taskId}")
    public ResponseEntity<ProjectResponse> updateProjectTask(@RequestBody TaskRequest taskRequest,@PathVariable Integer  projectId,@PathVariable Integer taskId) {
        return ResponseEntity.ok(projectTaskService.updateProjectTask(taskRequest, projectId, taskId));
    }

    @DeleteMapping("/deleteProjectTask/{projectId}/{taskId}")
    public ResponseEntity<String> deleteProjectTask(@PathVariable Integer projectId, @PathVariable Integer taskId) {
        return ResponseEntity.ok(projectTaskService.deleteProjectTask(projectId, taskId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProjectTaskResponse>> searchProject(@RequestParam(name = "name") String projectName) {
        return ResponseEntity.ok(projectService.searchProject(projectName));
    }

}
