package com.example.TaskManagement.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String taskname;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;
    @Column(nullable = false)
    private LocalDate deadline;
    @Column(nullable = false)
    private PRIORITY priority;
    @Column(nullable = false)
    private STATUS status;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "projectTasks")
    private List<Project> taskProjects;

}
