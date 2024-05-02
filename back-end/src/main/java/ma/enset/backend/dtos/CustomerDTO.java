package ma.enset.backend.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.backend.entities.Project;

import java.util.List;


@Data
@NoArgsConstructor@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String industry;
    private double revenue;
    private double estimatedRevenue;

}
