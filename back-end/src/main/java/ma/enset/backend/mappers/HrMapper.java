package ma.enset.backend.mappers;

import ma.enset.backend.dtos.CustomerDTO;
import ma.enset.backend.dtos.EmployeeDTO;
import ma.enset.backend.dtos.ProjectDTO;
import ma.enset.backend.dtos.TaskDTO;
import ma.enset.backend.entities.Customer;
import ma.enset.backend.entities.Employee;
import ma.enset.backend.entities.Project;
import ma.enset.backend.entities.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class HrMapper {
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public EmployeeDTO fromEmployee(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    public Employee fromEmployeeDTO(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    public ProjectDTO fromProject(Project project){
        ProjectDTO projectDTO = new ProjectDTO();
        BeanUtils.copyProperties(project, projectDTO);
        return projectDTO;
    }

    public Project fromProjectDTO(ProjectDTO projectDTO){
        Project project = new Project();
        BeanUtils.copyProperties(projectDTO, project);
        return project;
    }

    public TaskDTO fromTask(Task task){
        TaskDTO taskDTO = new TaskDTO();
        BeanUtils.copyProperties(task, taskDTO);
        return taskDTO;
    }

    public Task fromTaskDTO(TaskDTO taskDTO){
        Task task = new Task();
        BeanUtils.copyProperties(taskDTO, task);
        return task;
    }
}
