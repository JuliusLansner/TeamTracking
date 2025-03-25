package dk.teamtracker.teamtrackingbackend.repository;
import dk.teamtracker.teamtrackingbackend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Long> {
}
