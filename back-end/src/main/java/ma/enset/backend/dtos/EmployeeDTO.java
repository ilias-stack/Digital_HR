package ma.enset.backend.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.backend.entities.Project;
import ma.enset.backend.enums.EmployementStatus;

import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class EmployeeDTO {
    private String employeeName;
    private String email;
    private Long ID;
    private String state;
    private Date dob;
    private int age;
    private Date dateOfHire;
    private Date dateOfTermination;
    private String reasonForTerm;
    private EmployementStatus employmentStatus;
    private String department;
    private String position;
    private double payRate;
    private String managerName;
    private String employeeSource;
    private String performanceScore;
}
