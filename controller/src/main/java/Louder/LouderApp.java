package Louder;
import controller.UserController;
import entity.User;
import inteface.UserRepository;
import mapper.UserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service.UserServiceImpl;


@SpringBootApplication(scanBasePackageClasses = {
        LouderApp.class,
        UserController.class,
        UserMapper.class,
        UserRepository.class,
        UserServiceImpl.class})
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EntityScan(basePackageClasses = User.class)
public class LouderApp {
    public static void main(String[] args) {
        SpringApplication.run(LouderApp.class, args);
    }
}
