package ma.enset.backend.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.backend.entities.Employee;
import ma.enset.backend.entities.Project;
import ma.enset.backend.enums.TaskAvancement;
import ma.enset.backend.enums.TaskType;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class TaskDTO {
    private Long id;
    private TaskType taskType;
    private String Description;
    private Date startDate;
    private Date estimatedEndDate;
    private Date EndDate;
    private TaskAvancement taskAvancement;
    private Employee employee;
    private Project project;
}
