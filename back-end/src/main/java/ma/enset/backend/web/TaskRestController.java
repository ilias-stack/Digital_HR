package ma.enset.backend.web;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.TaskDTO;
import ma.enset.backend.services.DigitalHRService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TaskRestController {
    private DigitalHRService digitalHRService;
    @GetMapping("/tasks/search")
    public List<TaskDTO> searchTask(@PathParam("query") String s) {
        return digitalHRService.searchTask(s);
    }
}
