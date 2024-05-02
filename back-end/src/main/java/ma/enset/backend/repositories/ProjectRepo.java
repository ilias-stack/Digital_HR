package ma.enset.backend.repositories;


import ma.enset.backend.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Long> {
}
