package ma.enset.backend.repositories;


import ma.enset.backend.dtos.EmployeeDTO;
import ma.enset.backend.entities.Employee;
import ma.enset.backend.entities.Project;
import ma.enset.backend.entities.Task;
import ma.enset.backend.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProjectRepo extends JpaRepository<Project,Long> {
 List<Project> findProjectsByEmployees(List<Employee> employees);
 @Query("select c from Project c where c.title like :kw")
 List<Project> searchProject(@Param("kw") String keyword);

 @Query("SELECT p.projectStatus, COUNT(p) FROM Project p GROUP BY p.projectStatus")
 List<Object[]> countProjectsByStatus();

 @Query("SELECT p FROM Project p WHERE p.endDate >= :currentDate ORDER BY p.endDate ASC LIMIT 3")
 List<Project> findProjectsCloseToCurrentDate(@Param("currentDate") Date currentDate);
}
