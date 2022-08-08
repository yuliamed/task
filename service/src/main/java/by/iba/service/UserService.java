package by.iba.service;

import by.iba.dto.req.*;
import by.iba.dto.req.user.*;
import by.iba.dto.resp.ApiResp;
import by.iba.dto.resp.user.UserResp;
import by.iba.security.dto.JwtResp;

import javax.transaction.Transactional;

public interface UserService {
    UserResp signUp(SignUpReq userReq);

    UserResp confirmAccount(String token);

    JwtResp signIn(SignInReq signInReq);

    //ApiResp changePass(Long id, ChangePassDto dto);

    UserResp findByEmail(String email);

    UserResp getProfile(Long id);

    UserResp updateInfo(Long id, UserUpdateReq res);

    UserResp saveImage(Long id, ImageReq image);

    ApiResp recoveryPass(EmailReq email);

    ApiResp resetPass(ResetPassReq dto);

    UserResp deleteImage(Long id);
}
