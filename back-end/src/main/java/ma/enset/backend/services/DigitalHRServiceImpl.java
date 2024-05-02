package ma.enset.backend.services;

import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.EmployeeDTO;
import ma.enset.backend.dtos.ProjectDTO;
import ma.enset.backend.dtos.TaskDTO;
import ma.enset.backend.entities.Employee;
import ma.enset.backend.entities.Project;
import ma.enset.backend.entities.Task;
import ma.enset.backend.exceptions.EmployeeNotFoundException;
import ma.enset.backend.exceptions.ProjectNotFoundException;
import ma.enset.backend.exceptions.TaskNotFoundException;
import ma.enset.backend.mappers.HrMapper;
import ma.enset.backend.repositories.EmployeeRepo;
import ma.enset.backend.repositories.ProjectRepo;
import ma.enset.backend.repositories.TaskRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class DigitalHRServiceImpl implements DigitalHRService{
    private EmployeeRepo employeeRepo;
    private ProjectRepo projectRepo;
    private TaskRepo taskRepo;

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepo.findAll().stream().map(employee ->
            new HrMapper().fromEmployee(employee)).collect(Collectors.toList());
    }

    @Override
    public void addEmployeeToProject(Long employeeId, Long projectId) {
        Project project = projectRepo.findById(projectId).orElseThrow(()->new ProjectNotFoundException("project Not Found"));
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("employee not found"));
        employee.getProjects().add(project);
        project.getEmployees().add(employee);
        projectRepo.save(project);
        employeeRepo.save(employee);
    }

    @Override
    public void addTaskToProject(TaskDTO taskDTO,Long projectId,Long employeeId) {
        Project project =projectRepo.findById(projectId).orElseThrow(()->new ProjectNotFoundException("Project not found"));
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("employee not found"));
        HrMapper hrMapper = new HrMapper();
        Task task = hrMapper.fromTaskDTO(taskDTO);
        task.setProject(project);
        task.setEmployee(employee);
        taskRepo.save(task);
    }

    @Override
    public void removeEmployeeFromProject(Long employeeId, Long projectId) {
        Project project =projectRepo.findById(projectId).orElseThrow(()->new ProjectNotFoundException("Project not found"));
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("employee not found"));
        project.getEmployees().remove(employee);
        employee.getProjects().remove(project);
        employeeRepo.save(employee);
        projectRepo.save(project);
    }
}
