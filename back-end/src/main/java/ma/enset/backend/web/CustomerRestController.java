package ma.enset.backend.web;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.CustomerDTO;
import ma.enset.backend.services.DigitalHRService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class CustomerRestController {
    private DigitalHRService digitalHRService;
    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomers(@PathParam("query") String s) {
        return digitalHRService.searchCustomers(s);
    }
    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers(){
        return digitalHRService.getAllCustomers();
    }
    @PutMapping("/customers/{customerId}")
    public void updateCustomer(@PathVariable Long customerId, @RequestParam CustomerDTO customerDTO){
        digitalHRService.updateCustomer(customerId,customerDTO);
    }
    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        digitalHRService.deleteCustomer(customerId);
    }

}
