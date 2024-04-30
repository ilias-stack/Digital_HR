package ord.sid.backend.repositories;

import ord.sid.backend.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepo extends JpaRepository<Tache,Long> {
}
