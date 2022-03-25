import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitUtils implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = new ArrayList<>(
                Arrays.asList(
                        new User().setId(1l).setName("Igor"),
                        new User().setId(2l).setName("Tanya")
                )
        );

        userService.saveAll(users);
        for (User u : users) {
            System.out.println(u.getId() + " " + u.getName());
        }
    }
}
