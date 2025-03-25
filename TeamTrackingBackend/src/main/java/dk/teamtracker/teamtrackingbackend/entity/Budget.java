package dk.teamtracker.teamtrackingbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private String description;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;
    @ManyToOne
    @JoinColumn(name = "spent_by_id")
    private User spentBy;

    public Budget() {
        // no-arg
    }

    public Budget(Double amount, String description, Project project, Issue issue, User spentBy) {
        this.amount = amount;
        this.description = description;
        this.project = project;
        this.issue = issue;
        this.spentBy = spentBy;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
