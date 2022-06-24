package by.iba.mapper;

import by.iba.dto.req.SignUpReq;
import by.iba.dto.resp.UserResp;
import by.iba.entity.user.User;
import by.iba.mapper.common.FullAbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends FullAbstractMapper<User, UserResp, SignUpReq> {

    public UserMapper() {
        super(User.class, UserResp.class);
    }
}
