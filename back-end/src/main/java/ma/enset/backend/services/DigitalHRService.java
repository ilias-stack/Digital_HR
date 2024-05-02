package ma.enset.backend.services;

import ma.enset.backend.dtos.EmployeeDTO;
import ma.enset.backend.dtos.ProjectDTO;
import ma.enset.backend.dtos.TaskDTO;
import ma.enset.backend.entities.Employee;
import ma.enset.backend.entities.Project;
import ma.enset.backend.entities.Task;

import java.util.List;

public interface DigitalHRService {
    List<EmployeeDTO> getAllEmployee();
    void addEmployeeToProject(Long employeeId,Long projectId);
    void addTaskToProject(TaskDTO taskDTO, Long projectId,Long employeeId);
    void removeEmployeeFromProject(Long employeeId,Long projectId);
}
