package dk.teamtracker.teamtrackingbackend.entity;

import dk.teamtracker.teamtrackingbackend.util.IssueStatus;
import dk.teamtracker.teamtrackingbackend.util.IssueType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private IssueType type;
    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Issue(){}

    public Issue(String title,String description,IssueType type,IssueStatus status){
        this.title = title;
        this.description = description;
        this.type = type;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
