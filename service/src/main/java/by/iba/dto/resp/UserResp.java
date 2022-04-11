package by.iba.dto.resp;

import by.iba.dto.req.RoleReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserResp extends AbstractResp {
    private String name;
    private String email;
    private String surname;
    private Set<RoleReq> roles = new HashSet<>();
    private LocalDateTime lastVisitedDate;
    private String imageUrl;
    private Boolean isActive;
}
