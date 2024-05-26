package ma.enset.backend.services;

import ma.enset.backend.dtos.*;
import ma.enset.backend.entities.Employee;

import java.util.List;
import java.util.Map;

public interface DigitalHRService {
    List<EmployeeDTO> getAllEmployee();
    void addEmployeeToProject(Long employeeId,Long projectId);
    void addTaskToProject(TaskDTO taskDTO, Long projectId,Long employeeId);
    void removeEmployeeFromProject(Long employeeId,Long projectId);
    List<ProjectDTO> listProjectsByEmployees(List<Employee> employees);
    List<ProjectDTO> listProjectsByEmployee(Long employeeId);
    List<TaskDTO> taskListByProject(Long idProject);
    ProjectDTO addProject(ProjectDTO projectDTO, Long idCustomer, List<Long> employees);
    List<CustomerDTO> searchCustomers(String s);
    List<ProjectDTO> searchProjects(String s);
    List<EmployeeDTO> searchEmployee(String s);
    List<TaskDTO> searchTask(String s);
    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    List<ProjectDTO> getAllProjects();

    List<CustomerDTO> getAllCustomers();

    List<EmployeeDTO> getEmployeesByProject(Long projectId);

    List<TaskDTO> getTasksByEmployee(Long employeeId);

    void updateProject(Long projectId, ProjectDTO projectDTO);

    void updateEmployee(Long employeeId, EmployeeDTO employeeDTO);

    void updateTask(Long taskId, TaskDTO taskDTO);

    void updateCustomer(Long customerId, CustomerDTO customerDTO);

    void deleteProject(Long projectId);

    void deleteEmployee(Long employeeId);

    void deleteTask(Long taskId);

    void deleteCustomer(Long customerId);

    String scanUrl(String url);

    Map<String, Long> getCounts();

    List countProjectsByStatus();

    List<ProjectDTO> findProjectsCloseToCurrentDate();

    List findIncompleteTaskCountPerProject();

    List<TaskDTO> findTasksCloseToCurrentDate();

    List findEmployeeCountPerProject();
}
