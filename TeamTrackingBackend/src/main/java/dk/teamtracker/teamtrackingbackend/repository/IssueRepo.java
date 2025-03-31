package dk.teamtracker.teamtrackingbackend.repository;

import dk.teamtracker.teamtrackingbackend.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepo extends JpaRepository<Issue,Long> {
}
