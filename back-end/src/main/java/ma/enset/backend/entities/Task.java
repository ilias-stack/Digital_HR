package ma.enset.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.backend.enums.TaskProgress;
import ma.enset.backend.enums.TaskType;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TaskType taskType = TaskType.DEVELOPMENT;
    @Enumerated(EnumType.STRING)
    private TaskProgress taskProgress = TaskProgress.NOT_STARTED;
    private String Description;
    private Date startDate = new Date();
    private Date estimatedEndDate;
    private Date EndDate;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Project project;
}
