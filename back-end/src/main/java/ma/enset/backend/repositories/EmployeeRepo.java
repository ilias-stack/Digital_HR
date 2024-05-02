package ma.enset.backend.repositories;

import ma.enset.backend.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
}
