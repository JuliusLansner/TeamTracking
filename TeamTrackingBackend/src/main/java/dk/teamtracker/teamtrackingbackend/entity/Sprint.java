package dk.teamtracker.teamtrackingbackend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany
    private List<Issue> issues;


}
