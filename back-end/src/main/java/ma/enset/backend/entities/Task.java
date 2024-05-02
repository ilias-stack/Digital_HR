package ma.enset.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.backend.enums.TaskAvancement;
import ma.enset.backend.enums.TaskType;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TaskType taskType;
    private String Description;
    private Date startDate;
    private Date estimatedEndDate;
    private Date EndDate;
    private TaskAvancement taskAvancement;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Project project;
}
