package dk.teamtracker.teamtrackingbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String projectName;
    private String description;
    private double budgetLimit;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(mappedBy= "project", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Issue> issue;




}
