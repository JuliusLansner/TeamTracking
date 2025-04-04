package dk.teamtracker.teamtrackingbackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    public Project() {

    }
    public Project(Long id, String projectName, String description) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
    }
    @JsonProperty("projectName")
    private String projectName;
    @JsonProperty("description")
    private String description;
    private double budgetLimit;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy= "project", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Issue> issue;



}
