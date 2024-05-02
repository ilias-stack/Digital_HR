package ma.enset.backend.repositories;

import ma.enset.backend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Long> {
}
