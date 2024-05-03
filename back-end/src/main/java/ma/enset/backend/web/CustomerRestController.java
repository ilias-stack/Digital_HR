package ma.enset.backend.web;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.CustomerDTO;
import ma.enset.backend.services.DigitalHRService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController {
    private DigitalHRService digitalHRService;
    @GetMapping("/customers")
    public List<CustomerDTO> searchCustomers(@PathParam("query") String s) {
        return digitalHRService.searchCustomers(s);
    }
}
