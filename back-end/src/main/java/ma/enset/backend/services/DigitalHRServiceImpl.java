package ma.enset.backend.services;

import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.CustomerDTO;
import ma.enset.backend.dtos.EmployeeDTO;
import ma.enset.backend.dtos.ProjectDTO;
import ma.enset.backend.dtos.TaskDTO;
import ma.enset.backend.entities.Customer;
import ma.enset.backend.entities.Employee;
import ma.enset.backend.entities.Project;
import ma.enset.backend.entities.Task;
import ma.enset.backend.exceptions.CustomerNotFoundException;
import ma.enset.backend.exceptions.EmployeeNotFoundException;
import ma.enset.backend.exceptions.ProjectNotFoundException;
import ma.enset.backend.mappers.HrMapper;
import ma.enset.backend.repositories.CustomerRepo;
import ma.enset.backend.repositories.EmployeeRepo;
import ma.enset.backend.repositories.ProjectRepo;
import ma.enset.backend.repositories.TaskRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class DigitalHRServiceImpl implements DigitalHRService{
    private EmployeeRepo employeeRepo;
    private ProjectRepo projectRepo;
    private TaskRepo taskRepo;
    private CustomerRepo customerRepo;
    private HrMapper dtoMapper;

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
    //soulaimane --
    @Override
    public List<ProjectDTO> listProjectsByEmployees(List<Employee> employees
    ){
        List<Project> projectList=projectRepo.findProjectsByEmployees(employees);

        return projectList.stream().map(project ->
                new HrMapper().fromProject(project)).collect(Collectors.toList());
    }



    @Override
    public List<ProjectDTO> listProjectsByEmploye(Long employeeId){
        List<Employee> list = new ArrayList<>();
        Employee employee=employeeRepo.findEmployeeByID(employeeId);
        list.add(employee);
        return listProjectsByEmployees(list);
    }
    @Override
    public List<TaskDTO> taskListByProject(Long idProject){
        Project project=projectRepo.findById(idProject).orElseThrow(()->new ProjectNotFoundException("Project not found"));
        return project.getTasks().stream().map(task -> new HrMapper().fromTask(task)).collect(Collectors.toList());

    }
    @Override
    public ProjectDTO addProject(ProjectDTO projectDTO,Long idCustomer,List<Employee> employees,List<Task> tasks){
        Customer customer = customerRepo.findById(idCustomer).orElseThrow(()->new CustomerNotFoundException("Customer not found"));

        Project project= dtoMapper.fromProjectDTO(projectDTO);
        project.setCustomer(customer);
        project.setEmployees(employees);
        project.setTasks(tasks);
        Project saveProject =projectRepo.save(project);
        return dtoMapper.fromProject(saveProject);


    }
    @Override
    public List<CustomerDTO> searchCustomers(String s) {
        List<Customer> customers=customerRepo.searchCustomer(s);
        return customers.stream().map(cust -> dtoMapper.fromCustomer(cust)).collect(Collectors.toList());
    }
    @Override
    public List<ProjectDTO> searchProjects(String s) {
        List<Project> projectList=projectRepo.searchProject(s);
        return projectList.stream().map(prj -> dtoMapper.fromProject(prj)).collect(Collectors.toList());
    }
    @Override
    public List<EmployeeDTO> searchEmployee(String s) {
        List<Employee> employeeList=employeeRepo.searchEmployee(s);
        return employeeList.stream().map(em -> dtoMapper.fromEmployee(em)).collect(Collectors.toList());
    }
    @Override
    public List<TaskDTO> searchTask(String s) {
        List<Task> tasks=taskRepo.searchTask(s);
        return tasks.stream().map(ts -> dtoMapper.fromTask(ts)).collect(Collectors.toList());
    }
    // --
}
