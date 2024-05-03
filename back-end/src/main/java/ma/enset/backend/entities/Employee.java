package ma.enset.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Employee {
    private String employeeName;
    private String email;
    @Id
    private Long ID;
    private String state;
    private String zip;
    private Date dob;
    private int age;
    private char sex;
    private String maritalDesc;
    private String citizenDesc;
    private boolean hispanicLatino;
    private String raceDesc;
    private Date dateOfHire;
    private Date dateOfTermination;
    private String reasonForTerm;
    private String employmentStatus;
    private String department;
    private String position;
    private double payRate;
    private String managerName;
    private String employeeSource;
    private String performanceScore;
    @ManyToMany
    private List<Project> projects;
    @OneToMany
    private List<Task> tasks;
}
