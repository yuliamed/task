package by.iba.service;

import by.iba.dto.UserUpdate;
import by.iba.dto.resp.UserResp;
import by.iba.security.dto.JwtResp;
import by.iba.security.dto.SignInReq;
import by.iba.dto.req.SignUpReq;

public interface UserService {
    UserResp register(SignUpReq userReq);

    JwtResp signIn(SignInReq signInReq);

    UserResp findByEmail(String email);

    UserResp findById(Long id);

    UserResp updateInfo(Long id, UserUpdate res);

    UserResp saveImage(Long id, String imageUrl);
}
