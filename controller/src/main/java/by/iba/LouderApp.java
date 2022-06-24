package by.iba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"by.iba"})
public class LouderApp {
    public static void main(String[] args) {
        SpringApplication.run(LouderApp.class, args);

    }
}
