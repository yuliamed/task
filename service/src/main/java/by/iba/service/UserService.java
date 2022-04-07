package by.iba.service;

import by.iba.dto.ResetPassDTO;
import by.iba.dto.UserUpdate;
import by.iba.dto.req.SignUpReq;
import by.iba.dto.resp.ApiResp;
import by.iba.dto.resp.UserResp;
import by.iba.security.dto.JwtResp;
import by.iba.security.dto.SignInReq;

public interface UserService {
    UserResp register(SignUpReq userReq);

    UserResp confirmAccount(String token);

    JwtResp signIn(SignInReq signInReq);

    UserResp findByEmail(String email);

    UserResp findById(Long id);

    UserResp updateInfo(Long id, UserUpdate res);

    UserResp saveImage(Long id, String imageUrl);

    ApiResp recoveryPass(Long userId, String email);

    ApiResp resetPass(String token, ResetPassDTO dto);
}
