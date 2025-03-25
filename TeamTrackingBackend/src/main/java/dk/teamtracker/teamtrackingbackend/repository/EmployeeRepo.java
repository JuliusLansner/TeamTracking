package dk.teamtracker.teamtrackingbackend.repository;
import dk.teamtracker.teamtrackingbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<User,Long> {
}
