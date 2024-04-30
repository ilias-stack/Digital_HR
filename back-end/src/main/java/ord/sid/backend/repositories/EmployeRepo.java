package ord.sid.backend.repositories;

import ord.sid.backend.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepo extends JpaRepository<Employe,Long> {
}
