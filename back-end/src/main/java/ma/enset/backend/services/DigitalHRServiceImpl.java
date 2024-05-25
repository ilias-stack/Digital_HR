package ma.enset.backend.services;

import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.*;
import ma.enset.backend.entities.Customer;
import ma.enset.backend.entities.Employee;
import ma.enset.backend.entities.Project;
import ma.enset.backend.entities.Task;
import ma.enset.backend.exceptions.CustomerNotFoundException;
import ma.enset.backend.exceptions.EmployeeNotFoundException;
import ma.enset.backend.exceptions.ProjectNotFoundException;
import ma.enset.backend.exceptions.TaskNotFoundException;
import ma.enset.backend.mappers.HrMapper;
import ma.enset.backend.repositories.CustomerRepo;
import ma.enset.backend.repositories.EmployeeRepo;
import ma.enset.backend.repositories.ProjectRepo;
import ma.enset.backend.repositories.TaskRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
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
    private RestTemplate restTemplate;

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
    public List<ProjectDTO> listProjectsByEmployee(Long employeeId){
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
    public ProjectDTO addProject(ProjectDTO projectDTO,Long idCustomer,List<Long> employees){
        Customer customer = customerRepo.findById(idCustomer).orElseThrow(()->new CustomerNotFoundException("Customer not found"));
        Project project= dtoMapper.fromProjectDTO(projectDTO);
        List<Employee> employeeList = employeeRepo.findEmployeesByIDIn(employees);
        project.setCustomer(customer);
        project.setEmployees(employeeList);
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

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return dtoMapper.fromEmployee(employeeRepo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found")));
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepo.save(dtoMapper.fromEmployeeDTO(employeeDTO));
        return dtoMapper.fromEmployee(employee);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepo.findAll().stream().map(project -> dtoMapper.fromProject(project)).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getAllCustomers(){
        return customerRepo.findAll().stream().map(customer -> dtoMapper.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByProject(Long projectId){
        return employeeRepo.findEmployeesByProjectId(projectId).stream().map(employee -> dtoMapper.fromEmployee(employee)).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByEmployee(Long employeeId){
        return taskRepo.findByEmployeeID(employeeId).stream().map(task -> dtoMapper.fromTask(task)).collect(Collectors.toList());
    }

    @Override
    public void updateProject(Long projectId, ProjectDTO projectDTO){
        Project existingProject = projectRepo.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Employee not found with id: " + projectId));
        BeanUtils.copyProperties(existingProject,projectDTO,"id");
        projectRepo.save(existingProject);
    }

    @Override
    public void updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));
        BeanUtils.copyProperties(employeeDTO, existingEmployee,"ID");
        employeeRepo.save(existingEmployee);
    }

    @Override
    public void updateTask(Long taskId, TaskDTO taskDTO) {
        Task existingTask = taskRepo.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
        BeanUtils.copyProperties(taskDTO, existingTask,"id" );
        taskRepo.save(existingTask);
    }

    @Override
    public void updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepo.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));
        BeanUtils.copyProperties(customerDTO, existingCustomer, "id");
        customerRepo.save(existingCustomer);
    }

    @Override
    public void deleteProject(Long projectId){
        projectRepo.deleteById(projectId);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepo.deleteById(employeeId);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepo.deleteById(taskId);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepo.deleteById(customerId);
    }

    @Override
    public String scanUrl(String url) {
        String flaskUrl = "http://localhost:5000/api/process-url?url=" + url;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(
                flaskUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<String>() {}
        );

        return response.getBody();
    }

    @Override
    public Map<String, Long> getCounts() {
        Map<String, Long> counts = new HashMap<>();
        counts.put("totalCustomers", customerRepo.count());
        counts.put("totalEmployees", employeeRepo.count());
        counts.put("totalProjects", projectRepo.count());
        return counts;
    }

    @Override
    public List<Object[]> countProjectsByStatus(){
        return projectRepo.countProjectsByStatus();
    }

    @Override
    public List<ProjectDTO> findProjectsCloseToCurrentDate(){
        Date currentDate = new Date();

        return projectRepo.findProjectsCloseToCurrentDate(currentDate)
                .stream()
                .map(project -> dtoMapper.fromProject(project)).collect(Collectors.toList());
    }

    @Override
    public List findIncompleteTaskCountPerProject(){
        return taskRepo.findIncompleteTaskCountPerProject();
    }

    @Override
    public List<TaskDTO> findTasksCloseToCurrentDate() {
        Date currentDate = new Date();
        return taskRepo.findTasksCloseToCurrentDate(currentDate)
                .stream()
                .map(task -> dtoMapper.fromTask(task)).collect(Collectors.toList());
    }

}
