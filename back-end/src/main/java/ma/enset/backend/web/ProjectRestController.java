package ma.enset.backend.web;

import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.EmployeeDTO;
import ma.enset.backend.dtos.ProjectDTO;
import ma.enset.backend.dtos.TaskDTO;
import ma.enset.backend.services.DigitalHRService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class ProjectRestController {
    private DigitalHRService digitalHRService;

    @PostMapping("/projects/{projectId}/employees/{employeeId}/tasks")
    public void addTaskToProject(@RequestBody TaskDTO taskDTO, @PathVariable Long projectId, @PathVariable Long employeeId) {
        digitalHRService.addTaskToProject(taskDTO, projectId, employeeId);
    }
    @GetMapping("/projects/{projectId}/tasks")
    public List<TaskDTO> taskListByProject(@PathVariable Long projectId) {
        return digitalHRService.taskListByProject(projectId);
    }

    @PostMapping("/projects")
    public ProjectDTO addProject(@RequestBody ProjectDTO projectDTO, @RequestParam Long idCustomer,
                                 @RequestParam List<Long> employeeIds) {
        return digitalHRService.addProject(projectDTO, idCustomer, employeeIds);
    }
    @GetMapping("/projects/search")
    public List<ProjectDTO> searchProjects(@RequestParam String s) {
        return digitalHRService.searchProjects(s);
    }
    @GetMapping("/projects")
    public List<ProjectDTO> getAllProjects() {
        return digitalHRService.getAllProjects();
    }
    @GetMapping("/projects/{projectId}/employees")
    public List<EmployeeDTO> getAllEmployeesByProject(@PathVariable Long projectId){
        return digitalHRService.getEmployeesByProject(projectId);
    }
    @PutMapping("/projects/{projectId}")
    public void updateProject(@PathVariable Long projectId,@RequestBody ProjectDTO projectDTO){
        digitalHRService.updateProject(projectId,projectDTO);
    }
    @DeleteMapping("/projects/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        digitalHRService.deleteProject(projectId);
    }
}
