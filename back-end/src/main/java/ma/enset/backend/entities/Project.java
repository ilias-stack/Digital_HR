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
    private Date startDate = new Date();
    private Date estimatedEndDate;
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus = ProjectStatus.CREATED;
    private Date endDate;
    private double estimatedRevenue;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Employee> employees;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
    private Double customerRating;
}
