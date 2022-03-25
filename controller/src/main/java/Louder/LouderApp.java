package Louder;

import entity.User;
import inteface.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"entity"})
@EntityScan(basePackageClasses = User.class)
public class LouderApp {
    public static void main(String[] args) {
        SpringApplication.run(LouderApp.class, args);
    }
}
