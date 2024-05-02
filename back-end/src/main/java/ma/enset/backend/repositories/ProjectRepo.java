package ma.enset.backend.repositories;


import ma.enset.backend.dtos.EmployeeDTO;
import ma.enset.backend.entities.Employee;
import ma.enset.backend.entities.Project;
import ma.enset.backend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project,Long> {
 List<Project> findProjectsByEmployees(List<Employee> employees);
 @Query("select c from Project c where c.title like :kw")
 List<Project> searchProject(@Param("kw") String keyword);

}
