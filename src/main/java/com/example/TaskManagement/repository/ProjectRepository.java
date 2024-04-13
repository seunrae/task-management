package com.example.TaskManagement.repository;

import com.example.TaskManagement.dto.ProjectTaskResponse;
import com.example.TaskManagement.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT p FROM Project p WHERE p.projectname LIKE %:name%")
    List<Project> findByNameContaining(String name);
}
