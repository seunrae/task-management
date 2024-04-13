package com.example.TaskManagement.repository;

import com.example.TaskManagement.dto.TaskResponse;
import com.example.TaskManagement.models.STATUS;
import com.example.TaskManagement.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<List<Task>> findAllByStatus(STATUS status);
    List<Task> findByOrderByPriorityDesc();
    @Query("SELECT t FROM Task t WHERE t.taskname LIKE %:name%")
    List<Task> finByNameContaining(String name);
}
