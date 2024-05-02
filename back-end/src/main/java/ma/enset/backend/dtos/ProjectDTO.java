package ma.enset.backend.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.backend.entities.Customer;
import ma.enset.backend.entities.Employee;
import ma.enset.backend.entities.Task;
import ma.enset.backend.enums.ProjectStatus;

import java.util.Date;
import java.util.List;

@Data@AllArgsConstructor@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String title;
    private String Description;
    private Date startDate;
    private Date estimatedEndDate;
    private ProjectStatus projectStatus;
    private Date endDate;
    private double estimatedRevenue;
    private Double customerRating;
}
