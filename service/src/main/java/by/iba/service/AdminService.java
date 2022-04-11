package by.iba.service;

import by.iba.dto.req.RoleReq;
import by.iba.dto.req.UserBanReq;
import by.iba.dto.resp.UserResp;

import java.util.List;

public interface AdminService {
    UserResp banUser(Long id, UserBanReq userBanReq);

    List<UserResp> findAll();

    UserResp changeUserRole(Long userId, RoleReq roleReq);

    UserResp approveUser(Long id);
}
