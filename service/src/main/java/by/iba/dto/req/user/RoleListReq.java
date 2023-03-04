package by.iba.dto.req.user;

import by.iba.dto.req.AbstractReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleListReq extends AbstractReq {
    List<RoleReq> roles;
}
