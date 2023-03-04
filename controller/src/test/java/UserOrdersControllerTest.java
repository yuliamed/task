import by.iba.LouderApp;
import by.iba.controller.user.UserOrdersController;
import by.iba.security.jwt.EntryPointJwt;
import by.iba.security.jwt.JwtTokenProvider;
import by.iba.security.service.JwtUserDetailsService;
import by.iba.service.OrderService;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = LouderApp.class)
@WebMvcTest(UserOrdersController.class)
//@RunWith(SpringRunner.class)
public class UserOrdersControllerTest {

    @MockBean
    OrderService orderService;

    @MockBean
    JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    EntryPointJwt entryPointJwt;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(get("/").accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andExpect(status().isOk());
    }
}
