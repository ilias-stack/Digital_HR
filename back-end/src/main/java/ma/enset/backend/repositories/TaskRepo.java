package ma.enset.backend.repositories;

import ma.enset.backend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Long> {
    @Query("select c from Task c where c.Description like :kw")
    List<Task> searchTask(@Param("kw") String keyword);
}
