package mapper;

import dto.UserDTO;
import entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper mapper;

    public UserMapper() {
        mapper = new ModelMapper();
    }

    public UserDTO mapToUserDTO(User user) {
        UserDTO dto = mapper.map(user, UserDTO.class);
        return dto;
    }


}
