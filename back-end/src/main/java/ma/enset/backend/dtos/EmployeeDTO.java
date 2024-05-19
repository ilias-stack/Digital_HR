package ma.enset.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.backend.enums.EmploymentStatus;

import java.util.Date;

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
    private String department;
    private String position;
    private double payRate;
    private String managerName;
    private String employeeSource;
    private String performanceScore;
}
