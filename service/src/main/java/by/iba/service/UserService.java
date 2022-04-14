package by.iba.service;

import by.iba.dto.req.EmailReq;
import by.iba.dto.req.ResetPassReq;
import by.iba.dto.req.UserUpdateReq;
import by.iba.dto.req.SignUpReq;
import by.iba.dto.resp.ApiResp;
import by.iba.dto.resp.UserResp;
import by.iba.security.dto.JwtResp;
import by.iba.dto.req.SignInReq;

public interface UserService {
    UserResp signUp(SignUpReq userReq);

    UserResp confirmAccount(String token);

    JwtResp signIn(SignInReq signInReq);

    UserResp findByEmail(String email);

    UserResp findById(Long id);

    UserResp updateInfo(Long id, UserUpdateReq res);

    UserResp saveImage(Long id, String imageUrl);

    ApiResp recoveryPass(EmailReq email);

    ApiResp resetPass(String token, ResetPassReq dto);
}
