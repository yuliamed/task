package by.iba.service;

import by.iba.dto.RoleDTO;
import by.iba.dto.UserBanDTO;
import by.iba.dto.resp.UserResp;

import java.util.List;

public interface AdminService {
    UserResp banUser(Long id, UserBanDTO userBanDTO);

    List<UserResp> findAll();

    UserResp changeUserRole(Long userId, RoleDTO roleDTO);

    UserResp approveUser(Long id);
}
