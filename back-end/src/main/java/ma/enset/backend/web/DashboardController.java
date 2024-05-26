package ma.enset.backend.web;

import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.ProjectDTO;
import ma.enset.backend.dtos.TaskDTO;
import ma.enset.backend.services.DigitalHRService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {
    private final DigitalHRService digitalHRService;

    @GetMapping("/counts")
    public Map<String, Long> getCounts() {
        return digitalHRService.getCounts();
    }

    @GetMapping("/countProjectsByStatus")
    public List countProjectsByStatus() {
        return digitalHRService.countProjectsByStatus();
    }

    @GetMapping("/findProjectsCloseToCurrentDate")
    public List<ProjectDTO> findProjectsCloseToCurrentDate() {
        return digitalHRService.findProjectsCloseToCurrentDate();
    }

    @GetMapping("/findIncompleteTaskCountPerProject")
    public List findIncompleteTaskCountPerProject() {
        return digitalHRService.findIncompleteTaskCountPerProject();
    }

    @GetMapping("/findTasksCloseToCurrentDate")
    public List<TaskDTO> findTasksCloseToCurrentDate() {
        return digitalHRService.findTasksCloseToCurrentDate();
    }

    @GetMapping("/findEmployeeCountPerProject")
    public List findEmployeeCountPerProject() {
        return digitalHRService.findEmployeeCountPerProject();
    }

}
