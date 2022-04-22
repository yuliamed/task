package by.iba.service;

import by.iba.dto.req.*;
import by.iba.dto.resp.ApiResp;
import by.iba.dto.resp.UserResp;
import by.iba.security.dto.JwtResp;

public interface UserService {
    UserResp signUp(SignUpReq userReq);

    UserResp confirmAccount(String token);

    JwtResp signIn(SignInReq signInReq);

    UserResp findByEmail(String email);

    UserResp getProfile();

    UserResp updateInfo( UserUpdateReq res);

    UserResp saveImage( ImageReq image);

    ApiResp recoveryPass(EmailReq email);

    ApiResp resetPass(String token, ResetPassReq dto);

    UserResp deleteImage();
}
