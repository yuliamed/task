package by.iba.service;

import by.iba.dto.page.PageWrapper;
import by.iba.dto.req.user.RoleReq;
import by.iba.dto.req.user.UserBanReq;
import by.iba.dto.resp.user.UserResp;
import by.iba.dto.req.user.UserSearchCriteriaReq;

public interface AdminService {
    UserResp banUser(Long id, UserBanReq userBanReq);

    PageWrapper<UserResp> findAll(UserSearchCriteriaReq userSearchCriteriaReq);

    UserResp addUserRole(Long userId, RoleReq roleReq);

    UserResp approveUser(Long id);

}
