package ma.enset.backend.web;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import ma.enset.backend.dtos.UrlDTO;
import ma.enset.backend.services.DigitalHRService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class PhishingRestController {
    DigitalHRService digitalHRService;
    @GetMapping(value = "/phishing", produces = MediaType.APPLICATION_JSON_VALUE)
    public String scanUrl(@PathParam("url") String url){
        return digitalHRService.scanUrl(url);
    }
}
