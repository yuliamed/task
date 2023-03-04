package by.iba.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/mail")
public class HelloWorld {
    @RequestMapping("/")
    String gethelloWorld() {
        return "Hello World!";
    }

}
