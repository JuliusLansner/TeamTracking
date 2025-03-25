package dk.teamtracker.teamtrackingbackend.repository;
import dk.teamtracker.teamtrackingbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
