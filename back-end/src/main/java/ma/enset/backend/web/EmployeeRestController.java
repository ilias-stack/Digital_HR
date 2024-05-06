package ma.enset.backend.web;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.CustomerDTO;
import ma.enset.backend.dtos.EmployeeDTO;
import ma.enset.backend.dtos.ProjectDTO;
import ma.enset.backend.dtos.TaskDTO;
import ma.enset.backend.entities.Task;
import ma.enset.backend.services.DigitalHRService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class EmployeeRestController {
    private final DigitalHRService digitalHRService;

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() {
        return digitalHRService.getAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        return digitalHRService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return digitalHRService.saveEmployee(employeeDTO);
    }

    @PostMapping("/employees/{employeeId}/projects/{projectId}")
    public void addEmployeeToProject(@PathVariable Long employeeId, @PathVariable Long projectId) {
        digitalHRService.addEmployeeToProject(employeeId, projectId);
    }

    @DeleteMapping("/employees/{employeeId}/projects/{projectId}")
    public void removeEmployeeFromProject(@PathVariable Long employeeId, @PathVariable Long projectId) {
        digitalHRService.removeEmployeeFromProject(employeeId, projectId);
    }
    @GetMapping("/employees/{employeeId}/projects")
    public List<ProjectDTO> listProjectsByEmployee(@PathVariable Long employeeId) {
        return digitalHRService.listProjectsByEmployee(employeeId);
    }
    @GetMapping("/employees/search")
    public List<EmployeeDTO> searchEmployee(@PathParam("query") String s) {
        return digitalHRService.searchEmployee(s);
    }
    @GetMapping("/employees/{employeeId}/tasks")
    public List<TaskDTO> getTasksByEmployee(@PathVariable Long employeeId){
        return digitalHRService.getTasksByEmployee(employeeId);
    }
    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@PathVariable Long employeeId,@RequestBody EmployeeDTO employeeDTO){
        digitalHRService.updateEmployee(employeeId,employeeDTO);
    }
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        digitalHRService.deleteEmployee(employeeId);
    }
}
