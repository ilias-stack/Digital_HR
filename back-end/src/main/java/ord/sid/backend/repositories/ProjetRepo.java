package ord.sid.backend.repositories;

import ord.sid.backend.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepo extends JpaRepository<Projet,Long> {
}
