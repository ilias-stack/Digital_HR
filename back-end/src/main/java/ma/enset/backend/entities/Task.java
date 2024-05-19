package ma.enset.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.backend.enums.TaskType;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TaskType taskType = TaskType.DEVELOPMENT;
    private String Description;
    private Date startDate = new Date();
    private Date estimatedEndDate;
    private Date EndDate;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Project project;
}
