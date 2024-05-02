package ma.enset.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor@AllArgsConstructor
public class Customer {
    @Id
    private Long id;
    private String name;
    private String industry;
    private double revenue;
    private double estimatedRevenue;
    @OneToMany
    private List<Project> projects;

}
