package ma.enset.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String employeeName;
    private String projectName;
}
