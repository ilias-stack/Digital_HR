package ma.enset.backend.repositories;

import ma.enset.backend.entities.Employee;
import ma.enset.backend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Long> {
    @Query("select c from Task c where c.Description like :kw")
    List<Task> searchTask(@Param("kw") String keyword);

    List<Task> findByEmployeeID(Long employeeId);

    @Query("SELECT t.project.title, COUNT(t) FROM Task t WHERE t.taskProgress <> 'COMPLETED' GROUP BY t.project.title ORDER BY COUNT(t) DESC LIMIT 10")
    List<Object[]> findIncompleteTaskCountPerProject();


    @Query("SELECT t FROM Task t WHERE t.EndDate >= :currentDate AND t.taskProgress <> 'COMPLETED' ORDER BY t.EndDate ASC LIMIT 3")
    List<Task> findTasksCloseToCurrentDate(@Param("currentDate") Date currentDate);
}
