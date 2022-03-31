package by.iba.louder;
import by.iba.entity.User;
import by.iba.inteface.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import by.iba.service.impl.UserServiceImpl;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"by.iba"})
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EntityScan(basePackageClasses = User.class)
public class LouderApp {
    public static void main(String[] args) {
        SpringApplication.run(LouderApp.class, args);
    }
}
