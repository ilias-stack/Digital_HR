package ma.enset.backend;

import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.EmployeeDTO;
import ma.enset.backend.dtos.ProjectDTO;
import ma.enset.backend.entities.Customer;
import ma.enset.backend.entities.Employee;
import ma.enset.backend.entities.Project;
import ma.enset.backend.entities.Task;
import ma.enset.backend.repositories.CustomerRepo;
import ma.enset.backend.repositories.EmployeeRepo;
import ma.enset.backend.repositories.ProjectRepo;
import ma.enset.backend.repositories.TaskRepo;
import ma.enset.backend.services.DigitalHRService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
@AllArgsConstructor
public class BackEndApplication {
    private DigitalHRService digitalHRService;
    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }
    @Bean
    CommandLineRunner start(){
        return args -> {
            Stream.of("hamid","soulaymane","lfassi lostora","zaazaa").forEach(name->{
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setID((long)(Math.random()*1000));
                employeeDTO.setAge((int)(Math.random()*60));
                employeeDTO.setEmployeeName(name);
                employeeDTO.setEmail(name+"@email.com");
                digitalHRService.saveEmployee(employeeDTO);
            });
        };
    }

}
