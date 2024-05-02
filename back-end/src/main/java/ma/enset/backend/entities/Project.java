package ma.enset.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.backend.enums.ProjectStatus;

import java.util.Date;
import java.util.List;

@Entity
@Data@AllArgsConstructor@NoArgsConstructor
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String Description;
    private Date startDate;
    private Date estimatedEndDate;
    private ProjectStatus projectStatus;
    private Date endDate;
    private double estimatedRevenue;
    @ManyToOne
    private Customer customer;
    @ManyToMany
    private List<Employee> employees;
    @OneToMany
    private List<Task> tasks;
    private Double customerRating;
}
