package ma.enset.backend.web;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.TaskDTO;
import ma.enset.backend.services.DigitalHRService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")

public class TaskRestController {
    private DigitalHRService digitalHRService;
    @GetMapping("/tasks/search")
    public List<TaskDTO> searchTask(@PathParam("query") String s) {
        return digitalHRService.searchTask(s);
    }
    @PutMapping("/tasks/{taskId}")
    public void updateTask(@PathVariable Long taskId,@RequestBody TaskDTO taskDTO){
        digitalHRService.updateTask(taskId,taskDTO);
    }
    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        digitalHRService.deleteTask(taskId);
    }
}
